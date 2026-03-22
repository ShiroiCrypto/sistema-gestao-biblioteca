import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Persistência e regras de acesso a livros via JDBC (PreparedStatement).
 */
public class LivroService {

    private final LivroDAO livroDAO = new LivroDAO();

    private static final String SQL_DELETE = "DELETE FROM livros WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT id, titulo, autor, ano, categoria, status FROM livros ORDER BY id";

    /**
     * INSERT se {@code livro.getId() == 0}; caso contrário UPDATE (via {@link LivroDAO}).
     */
    public void salvar(Livro livro) throws SQLException {
        if (livro.getId() == 0) {
            livroDAO.adicionar(livro);
        } else {
            livroDAO.atualizar(livro);
        }
    }

    public void excluir(int id) throws SQLException {
        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public ArrayList<Livro> listarTodos() throws SQLException {
        ArrayList<Livro> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    /**
     * Busca por texto em título ou autor ({@code LIKE %filtro%}).
     * Se {@code filtro} for nulo ou vazio (após trim), retorna todos os registros.
     */
    public ArrayList<Livro> buscar(String filtro) throws SQLException {
        String t = filtro == null ? "" : filtro.trim();
        if (t.isEmpty()) {
            return listarTodos();
        }
        String padrao = "%" + t + "%";
        String sql = "SELECT id, titulo, autor, ano, categoria, status FROM livros "
                + "WHERE titulo LIKE ? OR autor LIKE ? ORDER BY id";
        ArrayList<Livro> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, padrao);
            ps.setString(2, padrao);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }

    /**
     * Pesquisa com filtros opcionais (painel superior). Campos vazios ou "Todos" não restringem.
     */
    public ArrayList<Livro> pesquisarComFiltros(String filtroTitulo, String filtroAutor, String filtroAno,
            String filtroCategoria, String filtroStatus) throws SQLException {
        StringBuilder sql = new StringBuilder(
                "SELECT id, titulo, autor, ano, categoria, status FROM livros WHERE 1=1");
        ArrayList<Object> params = new ArrayList<>();

        if (filtroTitulo != null && !filtroTitulo.trim().isEmpty()) {
            sql.append(" AND titulo LIKE ?");
            params.add("%" + filtroTitulo.trim() + "%");
        }
        if (filtroAutor != null && !filtroAutor.trim().isEmpty()) {
            sql.append(" AND autor LIKE ?");
            params.add("%" + filtroAutor.trim() + "%");
        }
        if (filtroAno != null && !filtroAno.trim().isEmpty()) {
            sql.append(" AND ano LIKE ?");
            params.add("%" + filtroAno.trim() + "%");
        }
        if (filtroCategoria != null && !filtroCategoria.trim().isEmpty() && !"Todos".equals(filtroCategoria)) {
            sql.append(" AND categoria = ?");
            params.add(filtroCategoria.trim());
        }
        if (filtroStatus != null && !filtroStatus.trim().isEmpty() && !"Todos".equals(filtroStatus)) {
            sql.append(" AND status = ?");
            params.add(filtroStatus.trim());
        }
        sql.append(" ORDER BY id");

        ArrayList<Livro> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }

    private static Livro mapear(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String titulo = rs.getString("titulo");
        String autor = rs.getString("autor");
        String ano = rs.getString("ano");
        if (ano == null) {
            ano = "";
        }
        String categoria = rs.getString("categoria");
        if (categoria == null) {
            categoria = "";
        }
        String status = rs.getString("status");
        if (status == null) {
            status = "";
        }
        return new Livro(id, titulo, autor, categoria, status, ano);
    }
}
