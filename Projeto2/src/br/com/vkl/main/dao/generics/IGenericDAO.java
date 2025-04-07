package br.com.vkl.main.dao.generics;

import br.com.vkl.main.domain.Persistente;
import reflections.anotacao.cadastro.exception.TipoChaveNaoEncontradaException;

import java.util.Collection;

/**
 * @author rodrigo.pires
 *
 * Interface genérica para métodos de CRUD(Create, Read, Update and Delete)
 */
public interface IGenericDAO <T extends Persistente> {


    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException;


    public void excluir(Long valor);


    public void alterar(T entity) throws TipoChaveNaoEncontradaException;


    public T consultar(Long valor);


    public Collection<T> buscarTodos();
}
