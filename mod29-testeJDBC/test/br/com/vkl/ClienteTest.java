package br.com.vkl;

import br.com.vkl.dao.ClienteDao;
import br.com.vkl.dao.IClienteDao;
import br.com.vkl.domain.Cliente;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClienteTest {

    @Test
    public void cadastrarTest() throws Exception {
        IClienteDao dao = new ClienteDao();

        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Rodrigao");

        Integer qtd = dao.cadastrar(cliente);
        assertTrue(qtd == 1);
//
//        Cliente clienteBD = dao.consultar(cliente.getCodigo());
//        assertNotNull(clienteBD);
//        assertNotNull(clienteBD.getId());
//        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
//        assertEquals(cliente.getNome(), clienteBD.getNome());
    }
}
