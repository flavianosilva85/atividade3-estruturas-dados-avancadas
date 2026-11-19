import java.util.Scanner;

/**
 * Curso: Análise e Desenvolvimento de Sistemas
 * Disciplina: Algoritmos e Estrutura de Dados
 * Unidade 3 - Atividade 3
 * 
 * Exercício 3: Busca Linear em Vetor de Contatos
 * 
 * Enunciado:
 * Você está criando um aplicativo de agenda de contatos e precisa encontrar
 * rapidamente a posição de um número específico na lista. Escreva um método
 * que realize uma busca linear em um array de inteiros, retornando a posição
 * do número buscado ou -1 caso ele não esteja presente.
 */
public class Exercicio03_BuscaLinear {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Array não ordenado simulando IDs de contatos telefônicos
        int[] idsContatos = { 98452, 91234, 97711, 99882, 93344, 95566, 92211, 96677 };

        System.out.println("==================================================");
        System.out.println("     AGENDA DE CONTATOS: BUSCA LINEAR SEQUENCIAL  ");
        System.out.println("==================================================");

        System.out.println("Lista de IDs de contatos cadastrados no sistema:");
        for (int i = 0; i < idsContatos.length; i++) {
            System.out.println(String.format(" Pos [%d] -> ID: %d", i, idsContatos[i]));
        }

        System.out.print("\nDigite o ID do contato que deseja localizar: ");
        if (scanner.hasNextInt()) {
            int idProcurado = scanner.nextInt();

            int posicao = buscaLinear(idsContatos, idProcurado);

            if (posicao != -1) {
                System.out.println("\n>> SUCESSO: Contato encontrado no índice " + posicao + " do vetor!");
            } else {
                System.out.println("\n>> RESULTADO: Contato ID " + idProcurado + " não encontrado na agenda (retorno -1).");
            }
        } else {
            System.out.println("Valor inválido! Digite apenas números inteiros.");
        }

        System.out.println("==================================================");
        scanner.close();
    }

    /**
     * Executa a Busca Linear (Sequencial) em um array de inteiros.
     * 
     * Funcionamento:
     * Percorre o vetor elemento por elemento, da posição inicial 0 até length - 1,
     * comparando cada valor com o elemento procurado.
     * 
     * Complexidade de Tempo:
     * - Melhor Caso : O(1) (quando o elemento está na primeira posição).
     * - Pior Caso   : O(N) (quando o elemento está na última posição ou não existe).
     * - Caso Médio  : O(N).
     * Complexidade de Espaço: O(1).
     * 
     * @param array Vetor de inteiros
     * @param valorBuscado Valor a ser localizado
     * @return Índice do elemento encontrado ou -1 se ausente
     */
    public static int buscaLinear(int[] array, int valorBuscado) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == valorBuscado) {
                return i; // Retorna o índice da primeira ocorrência
            }
        }
        return -1; // Elemento não encontrado no vetor
    }
}
