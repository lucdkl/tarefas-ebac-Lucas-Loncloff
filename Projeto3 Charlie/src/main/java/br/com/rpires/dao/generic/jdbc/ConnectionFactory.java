/**
 * 
 */
package br.com.rpires.dao.generic.jdbc;

import utils.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author rodrigo.pires
 *
 */
public class ConnectionFactory {
	
	private static Connection connection;

	private static String url = ConfigLoader.get("DB_URL");
	private static String user = ConfigLoader.get("DB_USER");
	private static String password = ConfigLoader.get("DB_PASS");

	private ConnectionFactory(Connection connection) {
	}
	
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = initConnection();
			return connection;
		} else if (connection.isClosed()) {
			connection = initConnection();
			return connection;
		} else {
			return connection;
		}
	}
	
	private static Connection initConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:15432/mod30Projeto3C",
					"lucas_admin", "9805");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
}
