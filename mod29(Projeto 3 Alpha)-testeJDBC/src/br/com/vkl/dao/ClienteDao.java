package br.com.vkl.dao;

import br.com.vkl.dao.generic.jdbc.ConnectionFactory;
import br.com.vkl.domain.Cliente;
import br.com.vkl.utils.SqlStatements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements IClienteDao {

    @Override
    public Integer cadastrar(Cliente cliente) throws Exception{
        Connection connection = null;
        PreparedStatement stm = null;
        try{
            connection = ConnectionFactory.getConnection();
            String sql = SqlStatements.getSqlInsert(SqlStatements.getTbCliente(), SqlStatements.getSQCliente());
            stm = connection.prepareStatement(sql);
            adicionarParametrosInsert(stm, cliente);
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
          closeConnection(connection,stm, null);
        }
    }


    @Override
    public Integer atualizar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try{
            connection = ConnectionFactory.getConnection();
            String sql = SqlStatements.getSqlUpdate(SqlStatements.getTbCliente());
            stm = connection.prepareStatement(sql);
            adicionarParametrosUpdate(stm, cliente);
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
            closeConnection(connection,stm, null);
        }
    }

    @Override
    public Cliente consultar(String codigo)  throws Exception{
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try{
            connection = ConnectionFactory.getConnection();
            String sql = SqlStatements.getSqlSearch(SqlStatements.getTbCliente());
            stm = connection.prepareStatement(sql);
            adicionarParametrosSelect(stm, codigo);
            rs = stm.executeQuery();
            if (rs.next()){
              cliente = new Cliente();
              cliente.setId(rs.getLong("id"));
              cliente.setCodigo(rs.getString("codigo"));
              cliente.setNome(rs.getString("nome"));
            }
            return cliente;
        }catch (Exception e){
            throw e;
        }finally {
           closeConnection(connection,stm,rs);
        }
    }

    @Override
    public List<Cliente> consultarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> list = new ArrayList<>();
        try{
            connection = ConnectionFactory.getConnection();
            String sql = SqlStatements.getSqlSearchAll(SqlStatements.getTbCliente());
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setCodigo(rs.getString("codigo"));
                cliente.setNome(rs.getString("nome"));
                list.add(cliente);
            }
        }catch (Exception e){
            throw e;
        }finally {
            closeConnection(connection,stm,rs);
        }
        return list;
    }

    @Override
    public Integer excluir(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try{
            connection = ConnectionFactory.getConnection();
            String sql = SqlStatements.getSqlDelete(SqlStatements.getTbCliente());
            stm = connection.prepareStatement(sql);
            adicionarParametrosDelete(stm, cliente);
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
          closeConnection(connection,stm,null);
        }
    }

    private void adicionarParametrosInsert(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getCodigo());
        stm.setString(2, cliente.getNome());
    }

    private void adicionarParametrosUpdate(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getNome());
        stm.setString(2, cliente.getCodigo());
        stm.setLong(3, cliente.getId());
    }

    private void adicionarParametrosDelete(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getCodigo());
    }

    private void adicionarParametrosSelect(PreparedStatement stm, String codigo) throws SQLException {
        stm.setString(1, codigo);
    }

    private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
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
}
