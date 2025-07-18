package br.com.vkl.dao;

import br.com.vkl.dao.generic.GenericDAO;
import br.com.vkl.domain.Cliente;
import br.com.vkl.domain.Produto;

public class ProdutoDAO extends GenericDAO<Produto, Long> implements IProdutoDAO {

    public ProdutoDAO() {
        super(Produto.class);
    }


}
