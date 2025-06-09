package br.com.vkl.dao.generic.jdbc;

import br.com.vkl.utils.ConfigLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection connection;

    private static String url = ConfigLoader.get("DB_URL");
    private static String user = ConfigLoader.get("DB_USER");
    private static String password = ConfigLoader.get("DB_PASS");

    private ConnectionFactory(Connection connection){

    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = initConnection();
        }else if (connection != null && connection.isClosed()){
            connection = initConnection();
        }return connection;
    }

    private static Connection initConnection() {
        try{
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
