package br.com.vkl.main.dao;

import br.com.vkl.main.domain.Cliente;

public interface IClienteDAO {
    Boolean salvar(Cliente cliente);

    Cliente buscarPorCpf(Long cpf);

    void excluir(Long cpf);

    void alterar(Cliente cliente);
}
