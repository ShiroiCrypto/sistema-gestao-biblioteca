import java.util.ArrayList;
import java.util.List;

/**
 * Serviço de negócio para cadastro e consulta de livros (armazenamento em memória).
 */
public class LivroService {

    private final List<Livro> livros = new ArrayList<>();
    private int proximoId = 1;

    public List<Livro> listarPorFiltroStatus(String filtroStatus) {
        if ("Todos".equals(filtroStatus)) {
            return new ArrayList<>(livros);
        }
        List<Livro> resultado = new ArrayList<>();
        for (Livro l : livros) {
            if (filtroStatus.equals(l.getStatus())) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    public void salvar(String titulo, String autor, String categoria, String status, String ano) {
        Livro novo = new Livro(proximoId++, titulo, autor, categoria, status, ano);
        livros.add(novo);
    }

    public void editar(int id, String titulo, String autor, String categoria, String status, String ano) {
        for (Livro l : livros) {
            if (l.getId() == id) {
                l.setTitulo(titulo);
                l.setAutor(autor);
                l.setCategoria(categoria);
                l.setStatus(status);
                l.setAno(ano);
                return;
            }
        }
    }

    public void excluir(int id) {
        livros.removeIf(l -> l.getId() == id);
    }
}
