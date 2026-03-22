import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gerencia conexões JDBC com o MySQL (XAMPP).
 */
public final class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_biblioteca"
            + "?useSSL=false&serverTimezone=America/Sao_Paulo&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Conexao() {
    }

    /**
     * Obtém uma conexão com o banco, carregando o driver MySQL explicitamente.
     */
    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC MySQL não encontrado no classpath.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
