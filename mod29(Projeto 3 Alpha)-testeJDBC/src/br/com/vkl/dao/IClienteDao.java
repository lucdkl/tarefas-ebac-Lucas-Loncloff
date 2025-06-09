package br.com.vkl.dao;

import br.com.vkl.domain.Cliente;

import java.util.List;

public interface IClienteDao {

    public Integer cadastrar(Cliente cliente) throws Exception;

    public Integer atualizar(Cliente cliente) throws Exception;

    public Cliente consultar(String codigo) throws Exception;

    public List<Cliente> consultarTodos() throws Exception;

    public Integer excluir(Cliente cliente) throws Exception;

}
