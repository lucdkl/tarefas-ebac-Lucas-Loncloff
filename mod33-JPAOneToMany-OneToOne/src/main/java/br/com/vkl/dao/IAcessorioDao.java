package br.com.vkl.dao;

import br.com.vkl.domain.Acessorio;

import java.util.List;

public interface IAcessorioDao {

    public Acessorio cadastrar(Acessorio acessorio);

    public Acessorio consultar (String codigo);

    public List<Acessorio> consultarTodos();

    public Acessorio atualizar (Acessorio acessorio);

    public void excluir (Acessorio acessorio);



}
