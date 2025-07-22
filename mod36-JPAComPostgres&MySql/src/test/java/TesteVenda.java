import br.com.vkl.dao.*;
import br.com.vkl.domain.Cliente;
import br.com.vkl.domain.Produto;
import br.com.vkl.domain.ProdutoQuantidade;
import br.com.vkl.domain.Venda;
import br.com.vkl.domain.Venda.Status;
import br.com.vkl.exceptions.DAOException;
import br.com.vkl.exceptions.MaisDeUmRegistroException;
import br.com.vkl.exceptions.TableException;
import br.com.vkl.exceptions.TipoChaveNaoEncontradaException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class TesteVenda {

    private IClienteDAO clienteDAO;

    private IProdutoDAO produtoDAO;

    private IVendaDAO vendaDAO;

    private VendaExclusaoDAO vendaExclusaoDAO;

    private String tel;

    private Long valor;

    private Cliente cliente;

    private Produto produto;

    public TesteVenda(){
        this.clienteDAO = new ClienteDAO();
        this.produtoDAO = new ProdutoDAO();
        this.vendaDAO = new VendaDAO();
        this.vendaExclusaoDAO = new VendaExclusaoDAO();
        tel = String.valueOf(ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L));
        valor = ThreadLocalRandom.current().nextLong(10L, 100L);
    }

    public Long gerarCodigo(){
       return ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L);
    }

    @Before
    public void init() throws DAOException, TipoChaveNaoEncontradaException {
        this.cliente = criarClienteHolder();
        this.produto = criarProdutoHolder( BigDecimal.TEN);
    }

    @After
    public void end() throws DAOException {
        excluirVendas();
        excluirProdutos();
        clienteDAO.excluir(this.cliente);
    }

    private Venda criarVenda(String codigo) throws DAOException, TipoChaveNaoEncontradaException {
        Venda venda = new Venda();
        venda.setCodigo(codigo);
        venda.setDataVenda(Instant.now());
        venda.setCliente(this.cliente);
        venda.setStatus(Status.INICIADA);
        venda.adicionarProduto(this.produto, 2);
        return venda;
    }


    public Produto criarProdutoHolder(BigDecimal valor) throws DAOException, TipoChaveNaoEncontradaException {
        Produto produto = new Produto();
        produto.setNome("vendaTeste");
        produto.setCodigo(gerarCodigo());
        produto.setValor(valor);
        produto.setDescricao("vendaTeste");
        produtoDAO.cadastrar(produto);
        return produto;
    }

    public Cliente criarClienteHolder() throws DAOException, TipoChaveNaoEncontradaException {
        Cliente cliente = new Cliente();
        cliente.setNome("teste");
        cliente.setCpf(gerarCodigo());
        cliente.setTel(tel);
        cliente.setCidade("vendaTeste");
        cliente.setEstado("vendaTeste");
        cliente.setEndereco("vendaTeste");
        cliente.setNumero("000");
        clienteDAO.cadastrar(cliente);
        return cliente;
    }

    private void excluirProdutos() throws DAOException {
        Collection<Produto> list = this.produtoDAO.buscarTodos();
        list.forEach(prod -> {
            try {
                this.produtoDAO.excluir(prod);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    private void excluirVendas() throws DAOException {
        Collection<Venda> list = this.vendaExclusaoDAO.buscarTodos();
        list.forEach(prod -> {
            try {
                this.vendaExclusaoDAO.excluir(prod);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testeSalvar() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {

        Venda venda = criarVenda("A2");
        Venda retorno = vendaDAO.cadastrar(venda);
        assertNotNull(retorno);

        assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(20)));
        assertTrue(venda.getStatus().equals(Status.INICIADA));

        Venda vendaConsultada = vendaDAO.consultar(venda.getId());
        assertTrue(vendaConsultada.getId() != null);
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());

    }

    @Test
    public void cancelarVenda() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A3";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDAO.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        retorno.setStatus(Status.CANCELADA);
        vendaDAO.cancelarVenda(venda);

        Venda vendaConsultada = vendaDAO.consultar(venda.getId());
        assertEquals(codigoVenda, vendaConsultada.getCodigo());
        assertEquals(Status.CANCELADA, vendaConsultada.getStatus());
    }

    @Test
    public void adicionarMaisProdutosDoMesmo() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A4";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDAO.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Venda vendaConsultada = vendaDAO.consultarComCollection(venda.getId());
        vendaConsultada.adicionarProduto(produto, 1);

        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(30).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
        assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
    }

    @Test
    public void adicionarMaisProdutosDiferentes() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A5";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDAO.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = criarProdutoHolder(BigDecimal.valueOf(50));
        assertNotNull(prod);

        //TODO Usando este método apra evitar a exception org.hibernate.LazyInitializationException
        // Ele busca todos os dados da lista pois a mesma por default é lazy
        Venda vendaConsultada = vendaDAO.consultarComCollection(venda.getId());
        vendaConsultada.adicionarProduto(prod, 1);

        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
        assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
    }

    @Test(expected = DAOException.class)
    public void salvarVendaMesmoCodigoExistente() throws TipoChaveNaoEncontradaException, DAOException {
        Venda venda = criarVenda("A6");
        Venda retorno = vendaDAO.cadastrar(venda);
        assertNotNull(retorno);

        Venda venda1 = criarVenda("A6");
        Venda retorno1 = vendaDAO.cadastrar(venda1);
        assertNull(retorno1);
        assertTrue(venda.getStatus().equals(Status.INICIADA));
    }

    @Test
    public void removerProduto() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A7";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDAO.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = criarProdutoHolder( BigDecimal.valueOf(50));
        assertNotNull(prod);

        Venda vendaConsultada = vendaDAO.consultarComCollection(venda.getId());
        vendaConsultada.adicionarProduto(prod, 1);
        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));


        vendaConsultada.removerProduto(prod, 1);
        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 2);
        valorTotal = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
        assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
    }

    @Test
    public void removerApenasUmProduto() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A8";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDAO.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = criarProdutoHolder(BigDecimal.valueOf(50));
        assertNotNull(prod);

        Venda vendaConsultada = vendaDAO.consultarComCollection(venda.getId());
        vendaConsultada.adicionarProduto(prod, 1);
        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));


        vendaConsultada.removerProduto(prod, 1);
        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 2);
        valorTotal = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));
        assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
    }

    @Test
    public void removerTodosProdutos() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A9";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDAO.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = criarProdutoHolder(BigDecimal.valueOf(50));
        assertNotNull(prod);

        Venda vendaConsultada = vendaDAO.consultarComCollection(venda.getId());
        vendaConsultada.adicionarProduto(prod, 1);
        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(vendaConsultada.getValorTotal().equals(valorTotal));


        vendaConsultada.removerTodosProdutos();
        assertTrue(vendaConsultada.getQuantidadeTotalProdutos() == 0);
        assertTrue(vendaConsultada.getValorTotal().equals(BigDecimal.valueOf(0)));
        assertTrue(vendaConsultada.getStatus().equals(Status.INICIADA));
    }

    @Test
    public void finalizarVenda() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A10";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDAO.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        venda.setStatus(Status.CONCLUIDA);
        vendaDAO.finalizarVenda(venda);

        Venda vendaConsultada = vendaDAO.consultarComCollection(venda.getId());
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
        assertEquals(Status.CONCLUIDA, vendaConsultada.getStatus());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void tentarAdicionarProdutosVendaFinalizada() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        String codigoVenda = "A11";
        Venda venda = criarVenda(codigoVenda);
        Venda retorno = vendaDAO.cadastrar(venda);
        assertNotNull(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        venda.setStatus(Status.CONCLUIDA);
        vendaDAO.finalizarVenda(venda);

        Venda vendaConsultada = vendaDAO.consultarComCollection(venda.getId());
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
        assertEquals(Status.CONCLUIDA, vendaConsultada.getStatus());

        vendaConsultada.adicionarProduto(this.produto, 1);

    }

}