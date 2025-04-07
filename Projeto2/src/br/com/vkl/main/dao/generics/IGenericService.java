package br.com.vkl.main.dao.generics;

import br.com.vkl.main.domain.Persistente;
import reflections.anotacao.cadastro.exception.TipoChaveNaoEncontradaException;

public interface IGenericService <T extends Persistente> {

    public Boolean salvar(T entity) throws TipoChaveNaoEncontradaException;

    public T buscarPorCpf(Long valor);

    public void excluir(Long valor);

    public void alterar(T entity) throws TipoChaveNaoEncontradaException;

}
