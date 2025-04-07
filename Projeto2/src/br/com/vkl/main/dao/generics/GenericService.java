package br.com.vkl.main.dao.generics;

import br.com.vkl.main.domain.Persistente;
import reflections.anotacao.cadastro.exception.TipoChaveNaoEncontradaException;


public abstract class GenericService<T extends Persistente> implements IGenericService {

    @Override
    public Boolean salvar(Persistente entity) throws TipoChaveNaoEncontradaException {
        return null;
    }

    @Override
    public Persistente buscarPorCpf(Long valor) {
        return null;
    }

    @Override
    public void excluir(Long valor) {

    }

    @Override
    public void alterar(Persistente entity) throws TipoChaveNaoEncontradaException {

    }
}
