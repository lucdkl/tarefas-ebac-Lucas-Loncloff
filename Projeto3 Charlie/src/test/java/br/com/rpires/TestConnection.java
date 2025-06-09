package br.com.rpires;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static br.com.rpires.dao.generic.jdbc.ConnectionFactory.getConnection;


public class TestConnection {

    @Test
    public void testeConexao() throws SQLException {
        Connection connection = getConnection();
    }
}
