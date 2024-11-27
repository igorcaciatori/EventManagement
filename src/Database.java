import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String URL = "jdbc:mysql://localhost:3306/gestaoeventos?createDatabaseIfNotExist=true";
    private final String USER = "root";
    private final String PASSWORD = "";

    public java.sql.Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
