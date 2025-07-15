package br.com.vkl.dao;

import br.com.vkl.domain.Carro;

import java.util.List;

public interface ICarroDao {

    public Carro cadastrar(Carro carro);

    public Carro consultar (String codigo);

    public List<Carro> consultarTodos();

    public Carro atualizar (Carro carro);

    public void excluir (Carro carro);

}
