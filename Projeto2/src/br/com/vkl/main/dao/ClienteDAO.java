package br.com.vkl.main.dao;

import br.com.vkl.main.domain.Cliente;

public class ClienteDAO implements IClienteDAO {
    @Override
    public Boolean salvar(Cliente cliente) {
        return true;
    }

    @Override
    public Cliente buscarPorCpf(Long cpf) {
        return null;
    }

    @Override
    public void excluir(Long cpf) {

    }

    @Override
    public void alterar(Cliente cliente) {

    }
}
