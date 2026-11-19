/**
 * Curso: Análise e Desenvolvimento de Sistemas
 * Disciplina: Algoritmos e Estrutura de Dados
 * Unidade 3 - Atividade 3
 * 
 * Exercício 1: Implementação de Árvore Binária para Biblioteca
 * 
 * Enunciado:
 * Imagine que você está desenvolvendo um sistema de gerenciamento de bibliotecas.
 * Crie uma classe em Java para representar uma árvore binária, onde cada nó
 * armazenará informações sobre um livro. Implemente métodos que permitam inserir
 * novos livros e percorrer a árvore em pré-ordem, pós-ordem e em ordem para listar
 * todos os livros disponíveis.
 */
public class Exercicio01_ArvoreBinariaBiblioteca {

    // Classe estática interna representando o Nó da Árvore Binária
    public static class NoLivro {
        Livro livro;
        NoLivro esquerda;
        NoLivro direita;

        public NoLivro(Livro livro) {
            this.livro = livro;
            this.esquerda = null;
            this.direita = null;
        }
    }

    // Raiz da árvore
    private NoLivro raiz;

    public Exercicio01_ArvoreBinariaBiblioteca() {
        this.raiz = null;
    }

    /**
     * Insere um novo livro na árvore utilizando o ID como chave de ordenação.
     */
    public void inserir(Livro novoLivro) {
        raiz = inserirRecursivo(raiz, novoLivro);
    }

    private NoLivro inserirRecursivo(NoLivro atual, Livro novoLivro) {
        if (atual == null) {
            return new NoLivro(novoLivro);
        }

        if (novoLivro.getId() < atual.livro.getId()) {
            atual.esquerda = inserirRecursivo(atual.esquerda, novoLivro);
        } else if (novoLivro.getId() > atual.livro.getId()) {
            atual.direita = inserirRecursivo(atual.direita, novoLivro);
        }

        return atual;
    }

    /**
     * Percurso Pré-Ordem (Pre-order): Raiz -> Subárvore Esquerda -> Subárvore Direita
     */
    public void percorrerPreOrdem() {
        System.out.println("\n--- PERCURSO PRÉ-ORDEM (Raiz -> Esquerda -> Direita) ---");
        preOrdemRecursivo(raiz);
        System.out.println();
    }

    private void preOrdemRecursivo(NoLivro no) {
        if (no != null) {
            System.out.println(" • " + no.livro);
            preOrdemRecursivo(no.esquerda);
            preOrdemRecursivo(no.direita);
        }
    }

    /**
     * Percurso Em Ordem (In-order): Subárvore Esquerda -> Raiz -> Subárvore Direita
     * (Retorna os livros em ordem crescente de código/ID)
     */
    public void percorrerEmOrdem() {
        System.out.println("\n--- PERCURSO EM ORDEM (Crescente por ID) ---");
        emOrdemRecursivo(raiz);
        System.out.println();
    }

    private void emOrdemRecursivo(NoLivro no) {
        if (no != null) {
            emOrdemRecursivo(no.esquerda);
            System.out.println(" • " + no.livro);
            emOrdemRecursivo(no.direita);
        }
    }

    /**
     * Percurso Pós-Ordem (Post-order): Subárvore Esquerda -> Subárvore Direita -> Raiz
     */
    public void percorrerPosOrdem() {
        System.out.println("\n--- PERCURSO PÓS-ORDEM (Esquerda -> Direita -> Raiz) ---");
        posOrdemRecursivo(raiz);
        System.out.println();
    }

    private void posOrdemRecursivo(NoLivro no) {
        if (no != null) {
            posOrdemRecursivo(no.esquerda);
            posOrdemRecursivo(no.direita);
            System.out.println(" • " + no.livro);
        }
    }

    public static void main(String[] args) {
        Exercicio01_ArvoreBinariaBiblioteca biblioteca = new Exercicio01_ArvoreBinariaBiblioteca();

        System.out.println("==================================================================");
        System.out.println("    SISTEMA DE BIBLIOTECA - ÁRVORE BINÁRIA DE LIVROS              ");
        System.out.println("==================================================================");

        // Inserção de livros de exemplo no acervo
        System.out.println("Cadastrando livros no catálogo da biblioteca...");
        biblioteca.inserir(new Livro(1050, "Estruturas de Dados e Algoritmos em Java", "Robert Lafore"));
        biblioteca.inserir(new Livro(1020, "Código Limpo (Clean Code)", "Robert C. Martin"));
        biblioteca.inserir(new Livro(1080, "Padrões de Projetos (GoF)", "Erich Gamma et al."));
        biblioteca.inserir(new Livro(1010, "Entendendo Algoritmos", "Aditya Bhargava"));
        biblioteca.inserir(new Livro(1035, "Java: Como Programar", "Deitel & Deitel"));
        biblioteca.inserir(new Livro(1070, "Arquitetura Limpa", "Robert C. Martin"));
        biblioteca.inserir(new Livro(1095, "Introdução aos Algoritmos", "Thomas Cormen"));

        // Execução dos três tipos de percurso solicitados
        biblioteca.percorrerEmOrdem();
        biblioteca.percorrerPreOrdem();
        biblioteca.percorrerPosOrdem();

        System.out.println("==================================================================");
        System.out.println("Conclusão: O percurso Em Ordem lista os livros ordenados por ID.");
        System.out.println("==================================================================");
    }
}
