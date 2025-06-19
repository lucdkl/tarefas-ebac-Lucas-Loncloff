package br.com.vkl.dao;

import br.com.vkl.domain.Curso;

import java.util.List;

public interface ICursoDao {

    public Curso cadastrar(Curso curso);

    public Curso consultar (String codigo);

    public List<Curso> consultarTodos();

    public Curso atualizar (Curso curso);

    public void excluir (Curso curso);

}
