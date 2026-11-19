import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Curso: Análise e Desenvolvimento de Sistemas
 * Disciplina: Algoritmos e Estrutura de Dados
 * Unidade 3 - Atividade 3
 * 
 * Exercício 6: Busca em Largura (BFS) para Menor Caminho em Grafo Não Ponderado
 * 
 * Enunciado:
 * No contexto do seu aplicativo de navegação, implemente o algoritmo de Busca
 * em Largura (BFS) para encontrar o caminho mais curto entre dois pontos em um
 * grafo não ponderado, ajudando os usuários a escolherem a melhor rota.
 */
public class Exercicio06_BFSGrafo {

    private Map<String, List<String>> adjacencias = new HashMap<String, List<String>>();

    public void adicionarConexao(String u, String v) {
        adjacencias.putIfAbsent(u, new ArrayList<String>());
        adjacencias.putIfAbsent(v, new ArrayList<String>());
        adjacencias.get(u).add(v);
        adjacencias.get(v).add(u); // Grafo não direcionado (mão dupla)
    }

    /**
     * Encontra o caminho mais curto entre 'origem' e 'destino' utilizando BFS.
     * Em grafos não ponderados, o BFS garante encontrar o trajeto com o MENOR número de arestas/saltos.
     * 
     * Complexidade de Tempo: O(V + E), onde V é o número de vértices e E de arestas.
     * Complexidade de Espaço: O(V) para armazenar fila, visitados e predecessores.
     * 
     * @param origem Ponto de partida
     * @param destino Ponto de chegada
     * @return Lista ordenada dos pontos do caminho mais curto, ou vazia se inalcançável.
     */
    public List<String> encontrarMenorCaminhoBFS(String origem, String destino) {
        if (!adjacencias.containsKey(origem) || !adjacencias.containsKey(destino)) {
            return Collections.emptyList();
        }

        Queue<String> fila = new LinkedList<String>();
        Set<String> visitados = new HashSet<String>();
        Map<String, String> predecessores = new HashMap<String, String>();

        fila.offer(origem);
        visitados.add(origem);

        boolean destinoEncontrado = false;

        while (!fila.isEmpty()) {
            String atual = fila.poll();

            if (atual.equals(destino)) {
                destinoEncontrado = true;
                break;
            }

            for (String vizinho : adjacencias.get(atual)) {
                if (!visitados.contains(vizinho)) {
                    visitados.add(vizinho);
                    predecessores.put(vizinho, atual); // Registra quem descobriu o vizinho
                    fila.offer(vizinho);
                }
            }
        }

        if (!destinoEncontrado) {
            return Collections.emptyList();
        }

        // Reconstrução do caminho ótimo a partir dos predecessores
        List<String> caminho = new ArrayList<String>();
        String passo = destino;
        while (passo != null) {
            caminho.add(passo);
            passo = predecessores.get(passo);
        }
        Collections.reverse(caminho); // Inverte para ficar de Origem -> Destino

        return caminho;
    }

    public static void main(String[] args) {
        Exercicio06_BFSGrafo app = new Exercicio06_BFSGrafo();

        // Malha viária da cidade
        app.adicionarConexao("Centro", "Bairro Norte");
        app.adicionarConexao("Centro", "Bairro Sul");
        app.adicionarConexao("Bairro Norte", "Shopping Plaza");
        app.adicionarConexao("Bairro Sul", "Universidade");
        app.adicionarConexao("Shopping Plaza", "Hospital Central");
        app.adicionarConexao("Universidade", "Hospital Central");
        app.adicionarConexao("Hospital Central", "Aeroporto");
        app.adicionarConexao("Bairro Sul", "Aeroporto"); // Rota expressa direta!

        System.out.println("==================================================================");
        System.out.println("     ROTA INTELIGENTE: BUSCA EM LARGURA (BFS) NO APLICATIVO       ");
        System.out.println("==================================================================");

        String partida = "Centro";
        String chegada = "Aeroporto";

        System.out.println("Calculando rota mais rápida (menor número de trechos)...");
        System.out.println("Origem : " + partida);
        System.out.println("Destino: " + chegada);

        List<String> rota = app.encontrarMenorCaminhoBFS(partida, chegada);

        if (!rota.isEmpty()) {
            System.out.println("\n>> ROTA OTIMIZADA LOCALIZADA (" + (rota.size() - 1) + " trechos / conexões):");
            for (int i = 0; i < rota.size(); i++) {
                System.out.print(rota.get(i));
                if (i < rota.size() - 1) {
                    System.out.print(" ──▶ ");
                }
            }
            System.out.println("\n\nPercurso: " + String.join(" -> ", rota));
        } else {
            System.out.println("\n>> Não foi possível encontrar uma rota transitável entre os dois pontos.");
        }

        System.out.println("==================================================================");
    }
}
