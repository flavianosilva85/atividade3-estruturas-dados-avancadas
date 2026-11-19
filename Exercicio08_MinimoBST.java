/**
 * Curso: Análise e Desenvolvimento de Sistemas
 * Disciplina: Algoritmos e Estrutura de Dados
 * Unidade 3 - Atividade 3
 * 
 * Exercício 8: Localização do Menor Valor em uma BST (Melhor Oferta de Produto)
 * 
 * Enunciado:
 * Imagine que você está analisando uma árvore binária de busca para identificar
 * o produto mais barato de uma lista. Escreva um método que encontre o menor
 * valor em uma árvore binária de busca, ajudando a determinar a melhor oferta disponível.
 */
public class Exercicio08_MinimoBST {

    // Nó da Árvore Binária de Busca estruturado pelo preço do produto
    public static class NoProduto {
        String nome;
        double preco;
        NoProduto esquerda;
        NoProduto direita;

        public NoProduto(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
            this.esquerda = null;
            this.direita = null;
        }
    }

    private NoProduto raiz;

    public void inserir(String nome, double preco) {
        raiz = inserirRecursivo(raiz, nome, preco);
    }

    private NoProduto inserirRecursivo(NoProduto atual, String nome, double preco) {
        if (atual == null) {
            return new NoProduto(nome, preco);
        }

        if (preco < atual.preco) {
            atual.esquerda = inserirRecursivo(atual.esquerda, nome, preco);
        } else if (preco > atual.preco) {
            atual.direita = inserirRecursivo(atual.direita, nome, preco);
        }

        return atual;
    }

    /**
     * Localiza o produto com o MENOR preço na Árvore Binária de Busca.
     * 
     * Propriedade Fundamental da BST:
     * Como todo valor à esquerda de qualquer nó é estritamente menor,
     * o menor valor global da árvore estará SEMPRE localizado no nó mais
     * à esquerda (folha mais profunda à esquerda).
     * 
     * Complexidade de Tempo:
     * - O(H), onde H é a altura da árvore (O(log N) em árvores balanceadas).
     * - Não é necessário percorrer toda a árvore nem subárvores direitas!
     * Complexidade de Espaço: O(1) iterativo.
     * 
     * @return O nó correspondente ao produto com menor preço
     */
    public NoProduto encontrarProdutoMaisBarato() {
        if (raiz == null) {
            return null; // Árvore vazia
        }

        NoProduto atual = raiz;
        // Caminha sucessivamente para a subárvore esquerda até encontrar o nó extremo
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }

        return atual;
    }

    public static void main(String[] args) {
        Exercicio08_MinimoBST catalogo = new Exercicio08_MinimoBST();

        System.out.println("==================================================================");
        System.out.println("     COMPARADOR DE PREÇOS: BUSCA DO MENOR VALOR EM BST            ");
        System.out.println("==================================================================");

        // Cadastro de produtos com seus respectivos preços
        catalogo.inserir("Teclado Mecânico RGB", 249.90);
        catalogo.inserir("Headset Gamer 7.1", 189.00);
        catalogo.inserir("Monitor 144Hz IPS", 1199.00);
        catalogo.inserir("Mousepad Speed Extra Grande", 49.90);
        catalogo.inserir("Mouse Óptico 3200 DPI", 79.50);
        catalogo.inserir("Cabo HDMI 2.0 Banhado a Ouro", 29.90);
        catalogo.inserir("Placa de Vídeo RTX 4060", 2250.00);

        System.out.println("Produtos cadastrados na Árvore Binária de Busca.");
        System.out.println("Buscando o produto de menor preço via navegação à extrema esquerda...");

        NoProduto maisBarato = catalogo.encontrarProdutoMaisBarato();

        System.out.println("\n------------------------------------------------------------------");
        if (maisBarato != null) {
            System.out.println("🎉 MELHOR OFERTA ENCONTRADA!");
            System.out.println("Produto : " + maisBarato.nome);
            System.out.printf("Preço   : R$ %.2f%n", maisBarato.preco);
        } else {
            System.out.println("Nenhum produto cadastrado no catálogo.");
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Conclusão: Em uma BST, o valor mínimo é obtido diretamente");
        System.out.println("deslocando-se sempre para o ponteiro 'esquerda' até encontrar nó nulo.");
        System.out.println("==================================================================");
    }
}
