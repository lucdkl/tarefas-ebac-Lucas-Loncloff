package br.com.vkl.testes;

import br.com.vkl.main.dao.ClienteDAOMock;
import br.com.vkl.main.dao.IClienteDAO;
import br.com.vkl.main.domain.Cliente;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClienteDAOTest {

    private IClienteDAO clienteDAO;

    private Cliente cliente;


    public ClienteDAOTest(){
        clienteDAO = new ClienteDAOMock();
    }

    @Before
    public void init(){
        cliente = new Cliente();
        cliente.setNome("Rodrigo");
        cliente.setCpf(1234567890L);
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
    }

    @Test
    public void salvarClienteDao(){
        Boolean bool = clienteDAO.salvar(cliente);
        Assert.assertTrue(bool);
    }

    @Test
    public void pesquisarClienteDao(){
        Cliente clienteConsultado = clienteDAO.buscarPorCpf(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void excluirClienteDao(){
        clienteDAO.excluir(cliente.getCpf());
    }

    @Test
    public void alterarClienteDao(){
        cliente.setNome("Teste da silva");
        clienteDAO.alterar(cliente);
        Assert.assertEquals("Teste da silva", cliente.getNome());
    }
}
