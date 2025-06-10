package br.com.rpires.dao.factory;

import br.com.rpires.domain.Estoque;
import br.com.rpires.domain.Produto;
import br.com.rpires.domain.ProdutoQuantidade;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EstoqueFactory {

    public static Estoque convert(ResultSet rs) throws SQLException {
        Estoque estoque = EstoqueFactory.convert(rs);
        estoque.setId(rs.getLong("ID"));
        estoque.setQuantidade(rs.getInt("QUANTIDADE"));
        return estoque;
    }
}
