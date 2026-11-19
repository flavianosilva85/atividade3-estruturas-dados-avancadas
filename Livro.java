/**
 * Curso: Análise e Desenvolvimento de Sistemas
 * Disciplina: Algoritmos e Estrutura de Dados
 * Unidade 3 - Atividade 3 (Exercício 1)
 * 
 * Classe que representa um Livro no acervo da biblioteca.
 * O código do livro (id) atua como chave de ordenação na Árvore Binária de Busca.
 */
public class Livro {

    private int id;
    private String titulo;
    private String autor;

    public Livro(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return String.format("[ID %04d] \"%s\" - Autor: %s", id, titulo, autor);
    }
}
