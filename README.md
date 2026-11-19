# Unyleya - Análise e Desenvolvimento de Sistemas
## Disciplina: Algoritmos e Estrutura de Dados (2º Período)
### Unidade 3 – Estruturas de Dados Avançadas | Atividade 3

---

## 👨‍💻 Identificação do Aluno
- **Aluno:** Flaviano Silva
- **Curso:** Análise e Desenvolvimento de Sistemas
- **Disciplina:** Algoritmos e Estrutura de Dados
- **Atividade:** Tarefa 3 (Unidade 3)
- **Linguagem:** Java (JDK 8 ou superior)

---

## 📋 Resumo dos Exercícios Desenvolvidos

Abaixo está o resumo dos 10 exercícios implementados com os conceitos avançados de árvores, buscas e grafos:

| Módulo | Exercício | Arquivo(s) | Algoritmo / Estrutura | Descrição da Solução |
| :--- | :--- | :--- | :--- | :--- |
| **Árvores** | **Ex. 1** | `Exercicio01_ArvoreBinariaBiblioteca.java`<br>`Livro.java` | Árvore Binária / Percursos | Gerenciamento de biblioteca com percursos Pré-Ordem, Em Ordem (ordenado por ID) e Pós-Ordem. |
| **Árvores** | **Ex. 2** | `Exercicio02_ValidacaoBST.java` | Validação de BST | Algoritmo recursivo com limites superior/inferior para validação de integridade de categorias de e-commerce. |
| **Buscas** | **Ex. 3** | `Exercicio03_BuscaLinear.java` | Busca Linear | Localização sequencial de IDs de contatos telefônicos com complexidade $O(N)$. |
| **Buscas** | **Ex. 4** | `Exercicio04_BuscaBinaria.java` | Busca Binária | Busca logarítmica $O(\log N)$ em array ordenado com rastreamento detalhado de intervalos e posições centrais. |
| **Grafos** | **Ex. 5** | `Exercicio05_RepresentacaoGrafo.java` | Lista de Adjacências | Modelagem de rede viária para roteador de aplicativo urbano mapeando vias de mão dupla e mão única. |
| **Grafos** | **Ex. 6** | `Exercicio06_BFSGrafo.java` | BFS (Busca em Largura) | Menor caminho em saltos com reconstrução por predecessores para trajeto mais rápido no mapa não ponderado. |
| **Grafos** | **Ex. 7** | `Exercicio07_DFSGrafo.java` | DFS (Busca em Profundidade) | Exploração viária completa em profundidade com indentação hierárquica e registro de vértices visitados. |
| **Desafios** | **Ex. 8** | `Exercicio08_MinimoBST.java` | Mínimo em BST | Localização do produto mais barato em tempo $O(H)$ navegando para a folha extrema à esquerda. |
| **Desafios** | **Ex. 9** | `Exercicio09_CaminhoMaisCurtoPonderado.java` | Grafo Ponderado (Dijkstra) | Extensão do BFS com `PriorityQueue` para encontrar rota com menor distância física total (km). |
| **Desafios** | **Ex. 10** | `Exercicio10_OrdenacaoTopologicaGrafo.java` | Ordenação Topológica (Kahn) | Agendamento sequencial de etapas de projeto de software respeitando dependências e graus de entrada. |

---

## 🛠️ Detalhamento dos Conceitos Implementados

### 1. Árvore Binária de Biblioteca (`Exercicio01_ArvoreBinariaBiblioteca.java`)
- **Conceitos:** Nó binário composto por ponteiros para subárvores esquerda e direita. Implementação recursiva dos 3 percursos fundamentais:
  - **Pré-Ordem:** Raiz $\rightarrow$ Esquerda $\rightarrow$ Direita.
  - **Em Ordem:** Esquerda $\rightarrow$ Raiz $\rightarrow$ Direita (garante listagem ordenada por ID).
  - **Pós-Ordem:** Esquerda $\rightarrow$ Direita $\rightarrow$ Raiz.

### 2. Validação de BST (`Exercicio02_ValidacaoBST.java`)
- **Conceitos:** Invariante de Árvore Binária de Busca. Propagação de intervalos abertos `(min, max)` garantindo que todos os elementos da subárvore esquerda sejam estritamente menores e todos da direita estritamente maiores que qualquer ancestral.

### 3. Busca Linear (`Exercicio03_BuscaLinear.java`)
- **Conceitos:** Varredura exaustiva $O(N)$ em estrutura indexada não ordenada. Retorna o índice da primeira ocorrência ou `-1` caso o elemento não exista.

### 4. Busca Binária (`Exercicio04_BuscaBinaria.java`)
- **Conceitos:** Algoritmo de Divisão e Conquista $O(\log N)$. Cálculo do ponto médio `inicio + (fim - inicio) / 2` evitando estouro de representação numérica e descarte de 50% dos dados restantes a cada passo.

### 5. Representação de Grafos (`Exercicio05_RepresentacaoGrafo.java`)
- **Conceitos:** Lista de Adjacências baseada em `Map<String, List<String>>`, ideal para grafos esparsos do mundo real, otimizando consumo de memória ($O(V + E)$) e simplificando consultas de vizinhança.

### 6. BFS para Menor Caminho (`Exercicio06_BFSGrafo.java`)
- **Conceitos:** Travessia em camadas com estrutura `Queue<String>`. Garante o menor caminho em grafos não ponderados (menor número de arestas percorridas) e reconstrói o trajeto por mapa de predecessores.

### 7. DFS em Grafos (`Exercicio07_DFSGrafo.java`)
- **Conceitos:** Exploração em profundidade recursiva com conjunto `Set<String>` para controle de visitados, evitando loops infinitos e mapeando todas as ramificações viárias disponíveis.

### 8. Mínimo em uma BST (`Exercicio08_MinimoBST.java`)
- **Conceitos:** Em uma BST ordenada por preço, o menor valor reside no nó mais à esquerda. O algoritmo itera apenas pelos ponteiros `esquerda` em tempo $O(H)$, sem inspecionar a árvore inteira.

### 9. Caminho Mais Curto em Grafo Ponderado (`Exercicio09_CaminhoMaisCurtoPonderado.java`)
- **Conceitos:** Adaptação da Busca em Largura para arestas com peso (distância em quilômetros). Utiliza `PriorityQueue` e técnica de relaxamento de arestas (Algoritmo de Dijkstra) para garantir o trajeto mais curto.

### 10. Ordenação Topológica (`Exercicio10_OrdenacaoTopologicaGrafo.java`)
- **Conceitos:** Algoritmo de Kahn para Grafos Acíclicos Dirigidos (DAG). Calcula o grau de entrada (*in-degree*) de cada vértice, enfileira os nós com grau 0 e gera o cronograma sequencial estrito de tarefas sem violar dependências prévias.

---

## 🚀 Como Compilar e Executar

No terminal (PowerShell ou CMD) dentro da pasta da Atividade 3:

```bash
# Compilar todos os arquivos Java:
javac *.java

# Executar cada exercício individualmente:
java Exercicio01_ArvoreBinariaBiblioteca
java Exercicio02_ValidacaoBST
java Exercicio03_BuscaLinear
java Exercicio04_BuscaBinaria
java Exercicio05_RepresentacaoGrafo
java Exercicio06_BFSGrafo
java Exercicio07_DFSGrafo
java Exercicio08_MinimoBST
java Exercicio09_CaminhoMaisCurtoPonderado
java Exercicio10_OrdenacaoTopologicaGrafo
```

---

## 📦 Entrega
Os arquivos estão prontos para submissão no portal da Unyleya via arquivo compactado `.zip` ou através do repositório no GitHub.
