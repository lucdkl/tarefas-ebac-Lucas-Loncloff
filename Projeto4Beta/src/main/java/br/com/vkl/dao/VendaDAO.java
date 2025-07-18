package br.com.vkl.dao;

import br.com.vkl.dao.generic.GenericDAO;
import br.com.vkl.domain.Produto;
import br.com.vkl.domain.Venda;

public class VendaDAO extends GenericDAO<Venda, String> implements IVendaDAO {

    public VendaDAO() {
        super(Venda.class);
    }


}
