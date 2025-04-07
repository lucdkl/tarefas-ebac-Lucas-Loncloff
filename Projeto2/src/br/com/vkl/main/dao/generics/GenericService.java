package br.com.vkl.main.dao.generics;

import br.com.vkl.main.domain.Persistente;
import reflections.anotacao.cadastro.exception.TipoChaveNaoEncontradaException;


public abstract class GenericService<T extends Persistente> implements IGenericService {

    public GenericDAO genericDao;

    @Override
    public Boolean salvar(Persistente entity) throws TipoChaveNaoEncontradaException {
        return genericDao.cadastrar(entity);
    }

    @Override
    public Persistente buscarPorCpf(Long valor) {
        return genericDao.consultar(valor);
    }

    @Override
    public void excluir(Long valor) {

    }

    @Override
    public void alterar(Persistente entity) throws TipoChaveNaoEncontradaException {

    }
}
