package br.com.vkl;

import br.com.vkl.dao.ClienteDao;
import br.com.vkl.dao.IClienteDao;
import br.com.vkl.domain.Cliente;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ClienteTest {

    @Test
    public void testCliente() throws Exception {

        IClienteDao dao = new ClienteDao();

        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Rodrigao");

        Integer qtd = dao.cadastrar(cliente);
        assertTrue(qtd == 1);

        Cliente clienteBD = dao.consultar(cliente.getCodigo());
        assertNotNull(clienteBD);
        assertNotNull(clienteBD.getId());
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        clienteBD.setNome("Rodrigueiro");
        Integer qtdUp = dao.atualizar(clienteBD);
        assertTrue(qtdUp == 1);

        List<Cliente> lista = dao.consultarTodos();
        assertNotNull(lista);


        Integer qtdDel = dao.excluir(clienteBD);
        assertNotNull(qtdDel == 1);
    }
}
