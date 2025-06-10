/**
 * 
 */
package br.com.rpires.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import anotacao.ColunaTabela;
import br.com.rpires.dao.generic.GenericDAO;
import br.com.rpires.domain.Produto;
import br.com.rpires.exceptions.*;

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoDAO extends GenericDAO<Produto, String> implements IProdutoDAO {

	private EstoqueDao estoqueDao = new EstoqueDao();

	public ProdutoDAO() {
		super();
	}

	@Override
	public Class<Produto> getTipoClasse() {
		return Produto.class;
	}

	@Override
	public void atualiarDados(Produto entity, Produto entityCadastrado) {
		entityCadastrado.setCodigo(entity.getCodigo());
		entityCadastrado.setDescricao(entity.getDescricao());
		entityCadastrado.setNome(entity.getNome());
		entityCadastrado.setMarca(entity.getMarca());
		entityCadastrado.setValor(entity.getValor());
	}

	@Override
	protected String getQueryInsercao() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO TB_PRODUTO ");
		sb.append("(ID, CODIGO, NOME, MARCA, DESCRICAO, VALOR)");
		sb.append("VALUES (nextval('sq_produto'),?,?,?,?,?)");
		return sb.toString();
	}

	@Override
	protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Produto entity) throws SQLException {
		stmInsert.setString(1, entity.getCodigo());
		stmInsert.setString(2, entity.getNome());
		stmInsert.setString(3, entity.getMarca());
		stmInsert.setString(4, entity.getDescricao());
		stmInsert.setBigDecimal(5, entity.getValor());
	}

	@Override
	protected String getQueryExclusao() {
		return "DELETE FROM TB_PRODUTO WHERE CODIGO = ?";
	}

	@Override
	protected void setParametrosQueryExclusao(PreparedStatement stmExclusao, String valor) throws SQLException {
		stmExclusao.setString(1, valor);
	}

	@Override
	protected String getQueryAtualizacao() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE TB_PRODUTO ");
		sb.append("SET CODIGO = ?,");
		sb.append("NOME = ?,");
		sb.append("MARCA = ?,");
		sb.append("DESCRICAO = ?,");
		sb.append("VALOR = ?");
		sb.append(" WHERE CODIGO = ?");
		return sb.toString();
	}

	@Override
	protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Produto entity) throws SQLException {
		stmUpdate.setString(1, entity.getCodigo());
		stmUpdate.setString(2, entity.getNome());
		stmUpdate.setString(3, entity.getMarca());
		stmUpdate.setString(4, entity.getDescricao());
		stmUpdate.setBigDecimal(5, entity.getValor());
		stmUpdate.setString(6, entity.getCodigo());
	}

	@Override
	protected void setParametrosQuerySelect(PreparedStatement stmExclusao, String valor) throws SQLException {
		stmExclusao.setString(1, valor);
	}

	@Override
	public void setEstoque(Produto produto, Integer qtd) {
		try {
			estoqueDao.setEstoque(produto.getId(), qtd);
		}catch (DAOException | SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public Integer consultarEstoque(Produto produto) {
        Integer estoque = null;
        try {
            estoque = estoqueDao.consultarEstoque(produto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estoque;
	}
}
