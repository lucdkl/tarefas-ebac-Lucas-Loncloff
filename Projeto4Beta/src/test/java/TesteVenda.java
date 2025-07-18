import br.com.vkl.dao.*;
import br.com.vkl.domain.Cliente;
import br.com.vkl.domain.Produto;
import br.com.vkl.domain.Venda;
import br.com.vkl.exceptions.DAOException;
import br.com.vkl.exceptions.MaisDeUmRegistroException;
import br.com.vkl.exceptions.TableException;
import br.com.vkl.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class TesteVenda {

    private IClienteDAO clienteDAO;

    private IProdutoDAO produtoDAO;

    private IVendaDAO vendaDAO;

    private String tel;

    private Long valor;

    public TesteVenda(){
        this.clienteDAO = new ClienteDAO();
        this.produtoDAO = new ProdutoDAO();
        this.vendaDAO = new VendaDAO();
        tel = String.valueOf(ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L));;
        valor = ThreadLocalRandom.current().nextLong(10L, 100L);
    }

    public Long gerarCodigo(){
       return ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L);
    }

    private Venda criarVenda(String codigo, BigDecimal valor) throws DAOException, TipoChaveNaoEncontradaException {
        Venda venda = new Venda();
        venda.setCodigo(codigo);
        venda.setDataVenda(Instant.now());
        venda.setCliente(criarClienteHolder());
        venda.setStatus(Venda.Status.INICIADA);
        venda.adicionarProduto(criarProdutoHolder(valor), 2);
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


    @Test
    public void testeGeral() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {




    }
}