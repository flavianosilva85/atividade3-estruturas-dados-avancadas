import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Curso: Análise e Desenvolvimento de Sistemas
 * Disciplina: Algoritmos e Estrutura de Dados
 * Unidade 3 - Atividade 3
 * 
 * Exercício 9: Caminho Mais Curto em Grafo Ponderado (BFS Adaptado / Dijkstra)
 * 
 * Enunciado:
 * Ao modificar seu algoritmo BFS, você precisa encontrar o caminho mais curto
 * entre dois pontos em um grafo ponderado, considerando as distâncias. Esse
 * ajuste é crucial para garantir que os usuários recebam as rotas mais eficientes.
 */
public class Exercicio09_CaminhoMaisCurtoPonderado {

    // Representação de uma via/aresta com peso (distância em quilômetros)
    public static class Aresta {
        String destino;
        double distanciaKm;

        public Aresta(String destino, double distanciaKm) {
            this.destino = destino;
            this.distanciaKm = distanciaKm;
        }
    }

    // Elemento auxiliar para a Fila de Prioridade (adaptação do BFS para pesos)
    public static class NoDistancia implements Comparable<NoDistancia> {
        String local;
        double distanciaAcumulada;

        public NoDistancia(String local, double distanciaAcumulada) {
            this.local = local;
            this.distanciaAcumulada = distanciaAcumulada;
        }

        @Override
        public int compareTo(NoDistancia outro) {
            return Double.compare(this.distanciaAcumulada, outro.distanciaAcumulada);
        }
    }

    private Map<String, List<Aresta>> adjacencias = new HashMap<String, List<Aresta>>();

    public void adicionarVia(String u, String v, double distanciaKm) {
        adjacencias.putIfAbsent(u, new ArrayList<Aresta>());
        adjacencias.putIfAbsent(v, new ArrayList<Aresta>());
        adjacencias.get(u).add(new Aresta(v, distanciaKm));
        adjacencias.get(v).add(new Aresta(u, distanciaKm)); // Mão dupla
    }

    /**
     * Adaptação do BFS para grafos com pesos positivos (Algoritmo de Dijkstra).
     * Substitui a fila simples (FIFO) por uma Fila de Prioridade (PriorityQueue),
     * garantindo que os nós com menor custo acumulado sejam expandidos primeiro.
     * 
     * Complexidade de Tempo: O((V + E) log V)
     * Complexidade de Espaço: O(V)
     * 
     * @param origem Ponto inicial da rota
     * @param destino Destino final desejado
     */
    public void calcularRotaMaisCurta(String origem, String destino) {
        Map<String, Double> distancias = new HashMap<String, Double>();
        Map<String, String> predecessores = new HashMap<String, String>();
        PriorityQueue<NoDistancia> filaPrioridade = new PriorityQueue<NoDistancia>();

        // Inicializa todas as distâncias com infinito
        for (String vertice : adjacencias.keySet()) {
            distancias.put(vertice, Double.POSITIVE_INFINITY);
        }

        distancias.put(origem, 0.0);
        filaPrioridade.offer(new NoDistancia(origem, 0.0));

        while (!filaPrioridade.isEmpty()) {
            NoDistancia atual = filaPrioridade.poll();
            String localAtual = atual.local;

            if (localAtual.equals(destino)) {
                break; // Menor caminho até o destino já garantido
            }

            if (atual.distanciaAcumulada > distancias.get(localAtual)) {
                continue; // Ignora rotas desatualizadas
            }

            for (Aresta vizinha : adjacencias.getOrDefault(localAtual, new ArrayList<Aresta>())) {
                double novaDistancia = distancias.get(localAtual) + vizinha.distanciaKm;

                // Relaxamento da aresta
                if (novaDistancia < distancias.get(vizinha.destino)) {
                    distancias.put(vizinha.destino, novaDistancia);
                    predecessores.put(vizinha.destino, localAtual);
                    filaPrioridade.offer(new NoDistancia(vizinha.destino, novaDistancia));
                }
            }
        }

        // Reconstrução do trajeto
        List<String> caminho = new ArrayList<String>();
        String passo = destino;
        while (passo != null) {
            caminho.add(passo);
            passo = predecessores.get(passo);
        }
        Collections.reverse(caminho);

        System.out.println("==================================================================");
        System.out.println("        ROTA OTIMIZADA POR DISTÂNCIA (GRAFO PONDERADO)            ");
        System.out.println("==================================================================");
        System.out.println("Origem : " + origem);
        System.out.println("Destino: " + destino);

        if (distancias.get(destino) != Double.POSITIVE_INFINITY) {
            System.out.printf("Distância Total Calculada: %.2f km%n", distancias.get(destino));
            System.out.println("\nTrajeto recomendado:");
            for (int i = 0; i < caminho.size(); i++) {
                System.out.print(caminho.get(i));
                if (i < caminho.size() - 1) {
                    System.out.print(" ──▶ ");
                }
            }
            System.out.println("\n\nPasso a passo da rota:");
            for (int i = 0; i < caminho.size() - 1; i++) {
                String de = caminho.get(i);
                String para = caminho.get(i + 1);
                double trechoKm = 0.0;
                for (Aresta a : adjacencias.get(de)) {
                    if (a.destino.equals(para)) {
                        trechoKm = a.distanciaKm;
                        break;
                    }
                }
                System.out.printf("  %dº Trecho: %s até %s (%.1f km)%n", (i + 1), de, para, trechoKm);
            }
        } else {
            System.out.println("Não foi encontrado trajeto transitável até o destino.");
        }
        System.out.println("==================================================================");
    }

    public static void main(String[] args) {
        Exercicio09_CaminhoMaisCurtoPonderado rotas = new Exercicio09_CaminhoMaisCurtoPonderado();

        // Mapeamento das vias urbanas com distâncias reais em km
        rotas.adicionarVia("Centro", "Bairro Norte", 7.5);
        rotas.adicionarVia("Centro", "Bairro Sul", 4.0);
        rotas.adicionarVia("Bairro Sul", "Anel Rodoviário", 6.2);
        rotas.adicionarVia("Bairro Norte", "Shopping Plaza", 3.1);
        rotas.adicionarVia("Shopping Plaza", "Aeroporto", 12.0);
        rotas.adicionarVia("Anel Rodoviário", "Aeroporto", 5.8); // Rota expressa mais rápida em distância!

        rotas.calcularRotaMaisCurta("Centro", "Aeroporto");
    }
}
