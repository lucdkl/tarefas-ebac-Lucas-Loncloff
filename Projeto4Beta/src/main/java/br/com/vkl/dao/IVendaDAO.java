package br.com.vkl.dao;

import br.com.vkl.dao.generic.IGenericDAO;
import br.com.vkl.domain.Produto;
import br.com.vkl.domain.Venda;
import br.com.vkl.exceptions.DAOException;
import br.com.vkl.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaDAO extends IGenericDAO<Venda, Long> {
    void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;

    Venda consultarComCollection(Long id);

    void finalizarVenda(Venda venda) throws DAOException, TipoChaveNaoEncontradaException;
}
