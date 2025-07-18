package br.com.vkl.dao;

import br.com.vkl.dao.generic.GenericDAO;
import br.com.vkl.domain.Cliente;

public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO {

    public ClienteDAO() {
        super(Cliente.class);
    }


}
