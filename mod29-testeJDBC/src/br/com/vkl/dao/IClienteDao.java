package br.com.vkl.dao;

import br.com.vkl.domain.Cliente;

public interface IClienteDao {

    public Integer cadastrar(Cliente cliente) throws Exception;

    Cliente consultar(String codigo) throws Exception;
}
