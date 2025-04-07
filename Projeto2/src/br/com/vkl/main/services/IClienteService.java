package br.com.vkl.main.services;

import br.com.vkl.main.domain.Cliente;

public interface IClienteService {

    Boolean salvar(Cliente cliente);

    Cliente buscarPorCpf(Long cpf);

    void excluir(Long cpf);

    void alterar(Cliente cliente);
}
