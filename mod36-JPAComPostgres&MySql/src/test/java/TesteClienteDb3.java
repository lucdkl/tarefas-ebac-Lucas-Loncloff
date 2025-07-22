import br.com.vkl.dao.ClienteDAODB2;
import br.com.vkl.dao.ClienteDAODB3;
import br.com.vkl.dao.IClienteDAO;
import br.com.vkl.domain.Cliente;
import br.com.vkl.exceptions.DAOException;
import br.com.vkl.exceptions.MaisDeUmRegistroException;
import br.com.vkl.exceptions.TableException;
import br.com.vkl.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class TesteClienteDb3 {

    private IClienteDAO clienteDAO;

    private Long codigo;

    private String tel;

    public TesteClienteDb3(){
        this.clienteDAO = new ClienteDAODB3();
        codigo = ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L);
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