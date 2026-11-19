import java.util.Scanner;

/**
 * Curso: Análise e Desenvolvimento de Sistemas
 * Disciplina: Algoritmos e Estrutura de Dados
 * Unidade 3 - Atividade 3
 * 
 * Exercício 4: Busca Binária em Vetor Ordenado
 * 
 * Enunciado:
 * Imagine que você está desenvolvendo um sistema de pesquisa de dados em uma
 * lista de números ordenados. Implemente um algoritmo de busca binária que encontre
 * a posição de um elemento específico em um array ordenado de inteiros, retornando
 * sua posição ou -1 se não for encontrado.
 */
public class Exercicio04_BuscaBinaria {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Vetor previamente ordenado (pré-requisito mandatório da Busca Binária)
        int[] numerosOrdenados = { 105, 120, 135, 150, 175, 200, 245, 290, 315, 380, 420, 500 };

        System.out.println("==================================================================");
        System.out.println("   SISTEMA DE PESQUISA: BUSCA BINÁRIA EM ARRAY ORDENADO           ");
        System.out.println("==================================================================");

        System.out.println("Base de dados ordenada:");
        for (int i = 0; i < numerosOrdenados.length; i++) {
            System.out.print(String.format("[%02d]:%d  ", i, numerosOrdenados[i]));
            if ((i + 1) % 6 == 0) System.out.println();
        }

        System.out.print("\n\nDigite o número que deseja pesquisar: ");
        if (scanner.hasNextInt()) {
            int chave = scanner.nextInt();

            System.out.println("\n--- RASTREAMENTO DOS PASSOS DA BUSCA BINÁRIA ---");
            int posicao = buscaBinariaComRastreamento(numerosOrdenados, chave);

            System.out.println("------------------------------------------------------------------");
            if (posicao != -1) {
                System.out.println(">> SUCESSO: Elemento " + chave + " encontrado na POSIÇÃO [" + posicao + "]!");
            } else {
                System.out.println(">> NÃO ENCONTRADO: O elemento " + chave + " não pertence à lista (retorno -1).");
            }
        } else {
            System.out.println("Entrada inválida.");
        }

        System.out.println("==================================================================");
        scanner.close();
    }

    /**
     * Executa a Busca Binária exibindo as subdivisões sucessivas do espaço de busca.
     * 
     * Funcionamento:
     * Baseia-se no paradigma de Divisão e Conquista. A cada comparação com o elemento central (meio),
     * metade do espaço de busca restante é descartada.
     * 
     * Complexidade de Tempo:
     * - Pior Caso   : O(log N)
     * - Melhor Caso : O(1) (quando o valor está exatamente no meio)
     * Complexidade de Espaço: O(1) iterativo.
     * 
     * @param array Vetor ordenado de inteiros
     * @param alvo Valor procurado
     * @return Índice do elemento encontrado ou -1 se ausente
     */
    public static int buscaBinariaComRastreamento(int[] array, int alvo) {
        int inicio = 0;
        int fim = array.length - 1;
        int iteracao = 1;

        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2; // Previne overflow aritmético de inteiros

            System.out.println(String.format("Passo %02d | Intervalo analisado: [%d .. %d] | Posição Central [%d] = %d",
                    iteracao, inicio, fim, meio, array[meio]));

            if (array[meio] == alvo) {
                System.out.println(" -> Elemento coincidente encontrado no centro!");
                return meio;
            }

            if (array[meio] < alvo) {
                System.out.println(" -> " + array[meio] + " < " + alvo + ". Descartando metade esquerda.");
                inicio = meio + 1; // Busca na metade direita
            } else {
                System.out.println(" -> " + array[meio] + " > " + alvo + ". Descartando metade direita.");
                fim = meio - 1;    // Busca na metade esquerda
            }

            iteracao++;
        }

        return -1; // Não encontrado
    }
}
