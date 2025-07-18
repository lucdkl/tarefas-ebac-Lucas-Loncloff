import br.com.vkl.dao.ClienteDAO;
import br.com.vkl.dao.IClienteDAO;
import br.com.vkl.domain.Cliente;
import br.com.vkl.exceptions.DAOException;
import br.com.vkl.exceptions.MaisDeUmRegistroException;
import br.com.vkl.exceptions.TableException;
import br.com.vkl.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TesteCliente {

    private IClienteDAO clienteDAO;

    private String codigo;

    private String tel;

    public TesteCliente(){
        this.clienteDAO = new ClienteDAO();
        codigo = String.valueOf(ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L));;
        tel = String.valueOf(ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L));;
    }

    private Cliente criarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("teste");
        cliente.setCpf(codigo);
        cliente.setTel(tel);
        cliente.setCidade("SaoPaulo");
        cliente.setEstado("SaoPaulinho");
        cliente.setEndereco("Inferno");
        cliente.setNumero("666");
        return cliente;
    }

    @Test
    public void testeGeral() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {

        Cliente cliente = criarCliente();

        Assert.assertNotNull(clienteDAO.cadastrar(cliente));

        cliente.setNome("teste da silva");

        Assert.assertNotNull(clienteDAO.alterar(cliente));

        Assert.assertEquals(clienteDAO.consultar(cliente.getId()).getId(), cliente.getId());

        Collection<Cliente> clienteList = clienteDAO.buscarTodos();

        Boolean existe = clienteList.stream().anyMatch(c -> c.getId().equals(cliente.getId()));

        Assert.assertTrue(existe);

        clienteDAO.excluir(cliente);

        Assert.assertNull(clienteDAO.consultar(cliente.getId()));

    }
}