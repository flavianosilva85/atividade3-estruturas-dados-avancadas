import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Curso: Análise e Desenvolvimento de Sistemas
 * Disciplina: Algoritmos e Estrutura de Dados
 * Unidade 3 - Atividade 3
 * 
 * Exercício 5: Representação de Grafos com Lista de Adjacências
 * 
 * Enunciado:
 * Você está desenvolvendo um sistema de roteamento para um aplicativo de navegação.
 * Crie uma classe que represente um grafo em Java, utilizando lista de adjacências
 * ou matriz de adjacências, para mapear as conexões entre diferentes locais.
 */
public class Exercicio05_RepresentacaoGrafo {

    // Estrutura de Lista de Adjacências: Vértice (Local) -> Lista de Vértices Adjacentes
    private Map<String, List<String>> listaAdjacencia;

    public Exercicio05_RepresentacaoGrafo() {
        this.listaAdjacencia = new HashMap<String, List<String>>();
    }

    /**
     * Adiciona um novo vértice (local/ponto de interesse) ao grafo.
     */
    public void adicionarLocal(String local) {
        listaAdjacencia.putIfAbsent(local, new ArrayList<String>());
    }

    /**
     * Cria uma aresta (via/estrada) conectando dois locais.
     * 
     * @param origem Local de partida
     * @param destino Local de chegada
     * @param bidirecional true para vias de mão dupla; false para sentido único
     */
    public void conectarLocais(String origem, String destino, boolean bidirecional) {
        adicionarLocal(origem);
        adicionarLocal(destino);

        listaAdjacencia.get(origem).add(destino);

        if (bidirecional) {
            listaAdjacencia.get(destino).add(origem);
        }
    }

    /**
     * Exibe o mapa completo de conexões do grafo de navegação.
     */
    public void exibirMapaConexoes() {
        System.out.println("==================================================================");
        System.out.println("        MAPA DE NAVEGAÇÃO URBANA - LISTA DE ADJACÊNCIAS          ");
        System.out.println("==================================================================");

        for (Map.Entry<String, List<String>> ponto : listaAdjacencia.entrySet()) {
            String local = ponto.getKey();
            List<String> vizinhos = ponto.getValue();

            System.out.println(String.format("📍 [%-20s] se conecta a %d local(is):", local, vizinhos.size()));
            for (String conexao : vizinhos) {
                System.out.println("     └──> " + conexao);
            }
        }
        System.out.println("==================================================================");
    }

    public static void main(String[] args) {
        Exercicio05_RepresentacaoGrafo mapa = new Exercicio05_RepresentacaoGrafo();

        // Mapeamento dos principais pontos da cidade
        mapa.conectarLocais("Centro Histórico", "Terminal Rodoviário", true);
        mapa.conectarLocais("Centro Histórico", "Shopping Plaza", true);
        mapa.conectarLocais("Terminal Rodoviário", "Hospital Central", true);
        mapa.conectarLocais("Shopping Plaza", "Universidade Federal", true);
        mapa.conectarLocais("Shopping Plaza", "Hospital Central", true);
        mapa.conectarLocais("Hospital Central", "Aeroporto Internacional", true);
        mapa.conectarLocais("Universidade Federal", "Parque Tecnológico", false); // Sentido único

        // Exibição do grafo montado
        mapa.exibirMapaConexoes();
    }
}
