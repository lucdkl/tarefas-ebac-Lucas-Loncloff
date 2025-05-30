import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {
        String url = System.getenv("db_url");
        String user = System.getenv("db_user");
        String password = System.getenv("db_pass");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("nao e hj que eu pulo da ponte !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}