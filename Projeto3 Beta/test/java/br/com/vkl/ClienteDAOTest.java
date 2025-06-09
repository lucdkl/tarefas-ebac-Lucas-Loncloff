package java.br.com.vkl;

import main.java.br.com.vkl.exceptions.DAOException;
import main.java.br.com.vkl.exceptions.MaisDeUmRegistroException;
import main.java.br.com.vkl.exceptions.TableException;
import main.java.br.com.vkl.exceptions.TipoChaveNaoEncontradaException;

public class ClienteDAOTest {

    public Cliente initCliente () {
        Cliente cliente = new Cliente();
        cliente.setCpf(12312312312L);
        cliente.setNome("Rodrigo");
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        return cliente;
    }



    public void pesquisarCliente() throws MaisDeUmRegistroException, TableException, TipoChaveNaoEncontradaException, DAOException {


    }
}
