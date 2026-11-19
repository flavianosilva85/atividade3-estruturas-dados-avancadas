/**
 * Curso: Análise e Desenvolvimento de Sistemas
 * Disciplina: Algoritmos e Estrutura de Dados
 * Unidade 3 - Atividade 3
 * 
 * Exercício 2: Validação de Árvore Binária de Busca (BST)
 * 
 * Enunciado:
 * Suponha que você tenha uma árvore que armazena a hierarquia de categorias
 * de produtos em um e-commerce. Escreva um método que verifique se essa árvore
 * binária é uma árvore binária de busca (BST), garantindo que os produtos
 * estejam organizados corretamente para facilitar a busca e navegação.
 */
public class Exercicio02_ValidacaoBST {

    // Nó que armazena o código numérico e nome da categoria do produto
    public static class NoCategoria {
        int codigo;
        String nome;
        NoCategoria esquerda;
        NoCategoria direita;

        public NoCategoria(int codigo, String nome) {
            this.codigo = codigo;
            this.nome = nome;
            this.esquerda = null;
            this.direita = null;
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("     E-COMMERCE: VALIDADOR DE ÁRVORE BINÁRIA DE BUSCA (BST)       ");
        System.out.println("==================================================================");

        // Cenário 1: Construção de uma Árvore Binária VÁLIDA como BST
        //              50 (Informática)
        //            /    \
        //     30 (Gamer)   70 (Smartphones)
        //       /     \
        // 20 (Audio) 40 (Cabos)
        NoCategoria arvoreValida = new NoCategoria(50, "Informática");
        arvoreValida.esquerda = new NoCategoria(30, "Gamer");
        arvoreValida.direita = new NoCategoria(70, "Smartphones");
        arvoreValida.esquerda.esquerda = new NoCategoria(20, "Áudio e Vídeo");
        arvoreValida.esquerda.direita = new NoCategoria(40, "Cabos e Acessórios");

        System.out.println("\n[CENÁRIO 1 - Árvore de Categorias 1]:");
        boolean resultado1 = ehBSTValida(arvoreValida);
        System.out.println("Resultado da Validação: " + (resultado1 ? "✅ VÁLIDA (É uma BST autêntica)" : "❌ INVÁLIDA"));
        System.out.println("Diagnóstico: Todos os nós à esquerda são menores e à direita são maiores que seus ancestrais.");

        // Cenário 2: Construção de uma Árvore INVÁLIDA
        // O nó 60 está na subárvore ESQUERDA do 50, violando a regra básica da BST
        //              50 (Informática)
        //            /    \
        //     30 (Gamer)   70 (Smartphones)
        //       /     \
        // 20 (Audio) 60 (Erro: 60 > 50 mas está à esquerda!)
        NoCategoria arvoreInvalida = new NoCategoria(50, "Informática");
        arvoreInvalida.esquerda = new NoCategoria(30, "Gamer");
        arvoreInvalida.direita = new NoCategoria(70, "Smartphones");
        arvoreInvalida.esquerda.esquerda = new NoCategoria(20, "Áudio e Vídeo");
        arvoreInvalida.esquerda.direita = new NoCategoria(60, "Categoria Inconsistente"); // Violação!

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("[CENÁRIO 2 - Árvore de Categorias 2 (Com inconsistência proposital)]:");
        boolean resultado2 = ehBSTValida(arvoreInvalida);
        System.out.println("Resultado da Validação: " + (resultado2 ? "✅ VÁLIDA" : "❌ INVÁLIDA (Não atende às propriedades de BST)"));
        System.out.println("Diagnóstico: O nó [60] está na subárvore esquerda da raiz [50], violando a invariante de BST.");

        System.out.println("==================================================================");
    }

    /**
     * Ponto de entrada público para validação de BST.
     * 
     * @param raiz Nó raiz da árvore a ser verificada
     * @return true se a árvore for uma BST válida; false caso contrário
     */
    public static boolean ehBSTValida(NoCategoria raiz) {
        return validarLimites(raiz, null, null);
    }

    /**
     * Validação recursiva com propagação de intervalos mínimos e máximos permitidos.
     * 
     * Invariante de BST:
     * - Para todo nó N, cada elemento na sua subárvore esquerda DEVE ser < N.chave
     * - Para todo nó N, cada elemento na sua subárvore direita DEVE ser > N.chave
     * 
     * Complexidade de Tempo: O(N), pois visita cada nó exatamente uma vez.
     * Complexidade de Espaço: O(H), onde H é a altura da árvore na pilha de recursão.
     */
    private static boolean validarLimites(NoCategoria no, Integer min, Integer max) {
        // Árvore vazia ou folha nula é considerada válida por definição
        if (no == null) {
            return true;
        }

        // Se o valor do nó violar o limite inferior permitido
        if (min != null && no.codigo <= min) {
            return false;
        }

        // Se o valor do nó violar o limite superior permitido
        if (max != null && no.codigo >= max) {
            return false;
        }

        // Subárvore esquerda deve ter valor máximo = no.codigo
        // Subárvore direita deve ter valor mínimo = no.codigo
        return validarLimites(no.esquerda, min, no.codigo) &&
               validarLimites(no.direita, no.codigo, max);
    }
}
