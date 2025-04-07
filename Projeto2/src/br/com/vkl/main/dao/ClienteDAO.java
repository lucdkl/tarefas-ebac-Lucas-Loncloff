package br.com.vkl.main.dao;

import br.com.vkl.main.dao.generics.GenericDAO;
import br.com.vkl.main.domain.Cliente;


public class ClienteDAO extends GenericDAO<Cliente> implements IClienteDAO {
    @Override
    public Class<Cliente> getTipoClasse() {
        return Cliente.class;
    }

    @Override
    public void atualiarDados(Cliente entity, Cliente entityCadastrado) {

    }
}

