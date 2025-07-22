package br.com.vkl.dao;

import br.com.vkl.dao.generic.GenericDAO;
import br.com.vkl.domain.Cliente;

public class ClienteDAODB3 extends GenericDAO<Cliente, Long> implements IClienteDAO {

    public ClienteDAODB3() {
        super(Cliente.class, "mod36charlieMysql");
    }


}
