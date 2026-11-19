import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Curso: Análise e Desenvolvimento de Sistemas
 * Disciplina: Algoritmos e Estrutura de Dados
 * Unidade 3 - Atividade 3
 * 
 * Exercício 10: Ordenação Topológica de Grafos (Planejamento de Tarefas Interdependentes)
 * 
 * Enunciado:
 * Por fim, implemente um algoritmo que ordene os vértices de um grafo de acordo
 * com a topologia das conexões entre eles. Essa ordenação pode ser útil em diversas
 * aplicações, como planejamento de projetos ou organização de tarefas interdependentes.
 */
public class Exercicio10_OrdenacaoTopologicaGrafo {

    // Grafo Direcionado Acíclico (DAG): Tarefa -> Tarefas que dependem dela
    private Map<String, List<String>> adjacencias = new HashMap<String, List<String>>();
    private Map<String, Integer> grauEntrada = new HashMap<String, Integer>();

    public void adicionarTarefa(String tarefa) {
        adjacencias.putIfAbsent(tarefa, new ArrayList<String>());
        grauEntrada.putIfAbsent(tarefa, 0);
    }

    /**
     * Define uma dependência direcionada: 'preRequisito' DEVE ser concluído
     * antes de 'tarefaDependente'.
     */
    public void adicionarDependencia(String preRequisito, String tarefaDependente) {
        adicionarTarefa(preRequisito);
        adicionarTarefa(tarefaDependente);

        adjacencias.get(preRequisito).add(tarefaDependente);
        grauEntrada.put(tarefaDependente, grauEntrada.get(tarefaDependente) + 1);
    }

    /**
     * Executa o Algoritmo de Ordenação Topológica (Algoritmo de Kahn baseado em Grau de Entrada).
     * 
     * Funcionamento:
     * 1. Identifica tarefas sem pré-requisitos pendentes (grau de entrada = 0) e insere na fila.
     * 2. Remove da fila, adiciona na sequência ordenada e reduz o grau de entrada dos dependentes.
     * 3. Quando a dependência de uma tarefa dependente zera, ela está liberada para execução e entra na fila.
     * 
     * Complexidade de Tempo: O(V + E)
     * Complexidade de Espaço: O(V)
     * 
     * @return Lista ordenada das tarefas respeitando todas as dependências
     */
    public List<String> executarOrdenacaoTopologica() {
        Queue<String> fila = new LinkedList<String>();
        List<String> ordemTopologica = new ArrayList<String>();

        // Enfileira todas as tarefas que não dependem de nenhuma outra inicial
        for (Map.Entry<String, Integer> entry : grauEntrada.entrySet()) {
            if (entry.getValue() == 0) {
                fila.offer(entry.getKey());
            }
        }

        while (!fila.isEmpty()) {
            String tarefaLiberada = fila.poll();
            ordemTopologica.add(tarefaLiberada);

            // Reduz o grau de entrada de todos os dependentes diretos
            for (String dependente : adjacencias.get(tarefaLiberada)) {
                int novoGrau = grauEntrada.get(dependente) - 1;
                grauEntrada.put(dependente, novoGrau);

                // Se todos os pré-requisitos foram satisfeitos, pode executar
                if (novoGrau == 0) {
                    fila.offer(dependente);
                }
            }
        }

        // Validação de integridade (Detecção de ciclo)
        if (ordemTopologica.size() != grauEntrada.size()) {
            System.out.println(">> ALERTA: Detectado ciclo de dependências no grafo! Ordenação impossível.");
            return new ArrayList<String>();
        }

        return ordemTopologica;
    }

    public static void main(String[] args) {
        Exercicio10_OrdenacaoTopologicaGrafo projeto = new Exercicio10_OrdenacaoTopologicaGrafo();

        System.out.println("==================================================================");
        System.out.println("   PLANEJAMENTO DE PROJETO: ORDENAÇÃO TOPOLÓGICA DE TAREFAS       ");
        System.out.println("==================================================================");

        // Mapeamento das etapas de desenvolvimento de um software
        projeto.adicionarDependencia("1. Requisitos do Sistema", "2. Modelagem do Banco de Dados");
        projeto.adicionarDependencia("1. Requisitos do Sistema", "3. Prototipagem da Interface (UI/UX)");
        projeto.adicionarDependencia("2. Modelagem do Banco de Dados", "4. Desenvolvimento da API Backend");
        projeto.adicionarDependencia("3. Prototipagem da Interface (UI/UX)", "5. Desenvolvimento do Frontend Web");
        projeto.adicionarDependencia("4. Desenvolvimento da API Backend", "6. Testes Integrados de Sistema");
        projeto.adicionarDependencia("5. Desenvolvimento do Frontend Web", "6. Testes Integrados de Sistema");
        projeto.adicionarDependencia("6. Testes Integrados de Sistema", "7. Homologação com o Cliente");
        projeto.adicionarDependencia("7. Homologação com o Cliente", "8. Deploy em Produção na Nuvem");

        System.out.println("Processando grafo acíclico dirigido (DAG) via Algoritmo de Kahn...");
        List<String> cronograma = projeto.executarOrdenacaoTopologica();

        System.out.println("\n================ CRONOGRAMA DE EXECUÇÃO SEQUENCIAL ===============");
        System.out.println("Ordem que garante que nenhuma tarefa seja iniciada sem pré-requisitos:");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < cronograma.size(); i++) {
            System.out.println(String.format(" Etapa %02d: %s", (i + 1), cronograma.get(i)));
        }
        System.out.println("==================================================================");
    }
}
