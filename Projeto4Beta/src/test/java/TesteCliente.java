import br.com.vkl.dao.ClienteDAO;
import br.com.vkl.dao.IClienteDAO;
import br.com.vkl.domain.Cliente;
import br.com.vkl.exceptions.DAOException;
import br.com.vkl.exceptions.MaisDeUmRegistroException;
import br.com.vkl.exceptions.TableException;
import br.com.vkl.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Test;

import java.util.Random;

public class TesteCliente {

    private IClienteDAO clienteDAO;

    private Random rd;

    private String codigo;

    public TesteCliente(){
        this.clienteDAO = new ClienteDAO();
        rd = new Random();
        codigo = String.valueOf(rd.nextLong());
    }

    private Cliente criarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("teste");
        cliente.setCpf(codigo);
        cliente.setCidade("SaoPaulo");
        cliente.setEstado("SaoPaulinho");
        cliente.setEnd("Inferno");
        cliente.setNumero("666");
        return cliente;
    }

    @Test
    public void testeGeral() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {

        Cliente cliente = criarCliente();

//        Assert.assertNotNull(clienteDAO.cadastrar(cliente));
//
//        cliente.setNome("teste da silva");
//
//        Assert.assertNotNull(clienteDAO.alterar(cliente));
//
//        Assert.assertEquals(clienteDAO.consultar(String.valueOf(cliente.getId())), cliente);

//        List<Cliente> listClientes = clientesDao.consultarTodos();
//
//        Boolean existe = listClientes.stream().anyMatch(c -> c.getCodigo().equals("A1"));
//
//        Assert.assertTrue(existe);
//
//        clienteDao.excluir(cliente);
//
//        Assert.assertNull(clientesDao.consultar());

    }
}