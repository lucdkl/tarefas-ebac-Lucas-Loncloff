package br.com.rpires.dao;

import anotacao.ColunaTabela;
import br.com.rpires.domain.Cliente;
import br.com.rpires.domain.Estoque;
import br.com.rpires.domain.Produto;
import br.com.rpires.exceptions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;

import static br.com.rpires.dao.generic.jdbc.ConnectionFactory.getConnection;

public class EstoqueDao {

    Estoque estoque;

    public boolean setEstoque(Long id, Integer estoque) throws SQLException, DAOException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = getConnection();
            stm = connection.prepareStatement(getQueryInsercao());
            setParametrosQueryInsercao(stm, id, estoque);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DAOException("ERRO CADASTRANDO OBJETO ", e);
        }
    }

    public Integer consultarEstoque(Produto produto) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        Integer estoque = null;
        try {
            estoqueValido(produto.getId());
            stm = connection.prepareStatement("SELECT * FROM TB_PRODUTO_ESTOQUE WHERE id_produto_fk = ?");
            setParametrosQuerySelect(stm, produto.getId());
            rs = stm.executeQuery();
            if (rs.next()){
              estoque = rs.getInt("quantidade");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnection(connection, stm, rs);
        }
        return estoque;
    }


    private void estoqueValido(Long id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        Long count = null;
        try {
            stm = connection.prepareStatement("SELECT count(*) FROM TB_PRODUTO_ESTOQUE WHERE id_produto_fk = ?");
            stm.setLong(1,id);
            rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getLong(1);
                if (count == 0) {
                    criarEstoque(id);
                }
            }
        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        }
    }

    private boolean criarEstoque(Long id) throws SQLException, DAOException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = getConnection();
            stm = connection.prepareStatement(getQueryInsercao());
            setParametrosQueryInsercao(stm, id, 0);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DAOException("ERRO CADASTRANDO OBJETO ", e);
        }
    }


    protected void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    private String getQueryInsercao() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_PRODUTO_ESTOQUE ");
        sb.append("(ID, ID_PRODUTO_FK, QUANTIDADE)");
        sb.append("VALUES (nextval('sq_estoque'),?,?)");
        return sb.toString();
    }

    private void setParametrosQueryInsercao(PreparedStatement stmInsert, Long id, Integer estoque) throws SQLException {
        stmInsert.setLong(1, id);
        stmInsert.setInt(2, estoque);
    }

    private void setParametrosQuerySelect(PreparedStatement stmSelect, Long valor) throws SQLException {
        stmSelect.setLong(1, valor);
    }
}
