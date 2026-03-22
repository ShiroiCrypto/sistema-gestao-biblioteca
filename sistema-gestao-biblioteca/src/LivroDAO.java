import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Acesso a dados (DAO) para a entidade Livro.
 */
public class LivroDAO {

    private static final String SQL_INSERT = "INSERT INTO livros (titulo, autor, ano, categoria, status) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE livros SET titulo = ?, autor = ?, ano = ?, categoria = ?, status = ? WHERE id = ?";

    /**
     * Insere um novo livro e preenche {@link Livro#setId(int)} com a chave gerada.
     */
    public void adicionar(Livro livro) throws SQLException {
        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setString(3, livro.getAno());
            ps.setString(4, livro.getCategoria());
            ps.setString(5, livro.getStatus());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    livro.setId(rs.getInt(1));
                }
            }
        }
    }

    public void atualizar(Livro livro) throws SQLException {
        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setString(3, livro.getAno());
            ps.setString(4, livro.getCategoria());
            ps.setString(5, livro.getStatus());
            ps.setInt(6, livro.getId());
            ps.executeUpdate();
        }
    }
}
