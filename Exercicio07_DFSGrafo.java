import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Curso: Análise e Desenvolvimento de Sistemas
 * Disciplina: Algoritmos e Estrutura de Dados
 * Unidade 3 - Atividade 3
 * 
 * Exercício 7: Busca em Profundidade (DFS) em Grafos
 * 
 * Enunciado:
 * Continuando no seu aplicativo de navegação, escreva um método para realizar
 * a Busca em Profundidade (DFS) em um grafo. Esse método deve exibir todos os
 * vértices visitados, permitindo que os usuários visualizem as possíveis rotas
 * de maneira mais detalhada.
 */
public class Exercicio07_DFSGrafo {

    private Map<String, List<String>> adjacencias = new HashMap<String, List<String>>();

    public void adicionarConexao(String u, String v) {
        adjacencias.putIfAbsent(u, new ArrayList<String>());
        adjacencias.putIfAbsent(v, new ArrayList<String>());
        adjacencias.get(u).add(v);
        adjacencias.get(v).add(u);
    }

    /**
     * Inicia a exploração por Busca em Profundidade (DFS).
     * 
     * Complexidade de Tempo: O(V + E)
     * Complexidade de Espaço: O(V) na pilha de chamadas
     * 
     * @param pontoInicial Vértice onde a exploração terá início
     */
    public void executarDFS(String pontoInicial) {
        System.out.println("==================================================================");
        System.out.println("    EXPLORAÇÃO VIÁRIA: BUSCA EM PROFUNDIDADE (DFS)                ");
        System.out.println("==================================================================");
        System.out.println("Ponto de partida da varredura: [" + pontoInicial + "]\n");

        Set<String> visitados = new HashSet<String>();
        List<String> ordemVisitacao = new ArrayList<String>();

        System.out.println("Sequência de exploração em profundidade:");
        dfsRecursivo(pontoInicial, visitados, ordemVisitacao, 0);

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("Resumo da Varredura Completa da Rede Viária:");
        System.out.println("Total de locais alcançados e explorados: " + ordemVisitacao.size());
        System.out.println("Ordem cronológica de visitação DFS: " + String.join(" -> ", ordemVisitacao));
        System.out.println("==================================================================");
    }

    private void dfsRecursivo(String verticeAtual, Set<String> visitados, List<String> ordem, int nivelProfundidade) {
        // Marca o vértice atual como visitado
        visitados.add(verticeAtual);
        ordem.add(verticeAtual);

        // Indentação visual proporcional à profundidade
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < nivelProfundidade; i++) {
            indent.append("   │");
        }
        System.out.println(indent + "──▶ Visitando nível " + nivelProfundidade + ": [" + verticeAtual + "]");

        // Itera sobre todos os vizinhos
        List<String> vizinhos = adjacencias.getOrDefault(verticeAtual, new ArrayList<String>());
        for (String vizinho : vizinhos) {
            if (!visitados.contains(vizinho)) {
                // Aprofunda a busca recursivamente
                dfsRecursivo(vizinho, visitados, ordem, nivelProfundidade + 1);
            }
        }
    }

    public static void main(String[] args) {
        Exercicio07_DFSGrafo mapa = new Exercicio07_DFSGrafo();

        // Estrutura viária da região
        mapa.adicionarConexao("Marco Zero", "Bairro Histórico");
        mapa.adicionarConexao("Marco Zero", "Porto Marítimo");
        mapa.adicionarConexao("Bairro Histórico", "Teatro Municipal");
        mapa.adicionarConexao("Teatro Municipal", "Museu de Belas Artes");
        mapa.adicionarConexao("Porto Marítimo", "Distrito Industrial");
        mapa.adicionarConexao("Distrito Industrial", "Centro Logístico");

        // Execução do DFS a partir do Marco Zero
        mapa.executarDFS("Marco Zero");
    }
}
