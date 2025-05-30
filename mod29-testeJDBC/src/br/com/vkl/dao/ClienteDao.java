package br.com.vkl.dao;

import br.com.vkl.dao.generic.jdbc.ConnectionFactory;
import br.com.vkl.domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClienteDao implements IClienteDao {

    @Override
    public Integer cadastrar(Cliente cliente) throws Exception{
        Connection connection = null;
        PreparedStatement stm = null;
        try{
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO TB_CLIENTE (ID,CODIGO,NOME) VALUES (nextval('sq_cliente'),?,?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, cliente.getCodigo());
            stm.setString(2, cliente.getNome());
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Cliente consultar(String codigo)  throws Exception{
        return null;
    }
}
