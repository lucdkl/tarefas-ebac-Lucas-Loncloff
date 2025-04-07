package br.com.vkl.testes;

import br.com.vkl.main.dao.ClienteDAOMock;
import br.com.vkl.main.dao.IClienteDAO;
import br.com.vkl.main.domain.Cliente;
import br.com.vkl.main.services.ClienteService;
import br.com.vkl.main.services.IClienteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import reflections.anotacao.cadastro.exception.TipoChaveNaoEncontradaException;

public class ClienteServiceTest {

    private IClienteService clienteService;

    private Cliente cliente;

    public ClienteServiceTest(){
        IClienteDAO dao = new ClienteDAOMock();
        clienteService = new ClienteService(dao);
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
    public void salvarClienteService() throws TipoChaveNaoEncontradaException {
        Boolean bool = clienteService.salvar(cliente);
        Assert.assertTrue(bool);
    }

    @Test
    public void pesquisarClienteService(){
        Cliente clienteConsultado = clienteService.buscarPorCpf(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void excluirClienteService(){
        clienteService.excluir(cliente.getCpf());
    }

    @Test
    public void alterarClienteService() throws TipoChaveNaoEncontradaException {
        cliente.setNome("Teste da silva");
        clienteService.alterar(cliente);
        Assert.assertEquals("Teste da silva", cliente.getNome());
    }
}
