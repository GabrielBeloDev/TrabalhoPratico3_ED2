import java.util.*;

public class DFS {
    private Grafo grafo; // referência ao grafo
    private int tempo; // contador para tempo de descoberta e finalização
    private int[] cor; // cor dos vértices (BRANCO, CINZENTO, PRETO)
    private int[] pais; // pais dos vértices na árvore DFS
    private int[] tempo_descoberta; // armazena tempo de descoberta dos vértices
    private int[] tempo_finalizacao; // armazena tempo de finalização dos vértices
    private List<String> arestasDeRetorno; // lista de arestas de retorno

    private static final int BRANCO = 0; // vértice não visitado
    private static final int CINZENTO = 1; // vértice em processo de visitação
    private static final int PRETO = 2; // vértice completamente visitado

    // construtor para inicializar os atributos do DFS
    public DFS(Grafo grafo) {
        this.grafo = grafo; // inicializa o grafo
        this.tempo = 0; // inicializa o tempo
        this.cor = new int[grafo.getVertices()]; // inicializa o array de cores dos vértices
        this.pais = new int[grafo.getVertices()]; // inicializa o array de pais dos vértices
        this.tempo_descoberta = new int[grafo.getVertices()]; // inicializa o array de tempos de descoberta
        this.tempo_finalizacao = new int[grafo.getVertices()]; // inicializa o array de tempos de finalização
        this.arestasDeRetorno = new ArrayList<>(); // inicializa a lista de arestas de retorno
        Arrays.fill(pais, -1); // define todos os pais como -1 (sem pai)
    }

    // método para executar a DFS em todos os vértices do grafo
    public void dfs() {
        for (int u = 0; u < grafo.getVertices(); u++) {
            cor[u] = BRANCO; // marca todos os vértices como não visitados
            pais[u] = -1; // define todos os pais como -1
        }
        tempo = 0; // reinicializa o tempo
        for (int u = 0; u < grafo.getVertices(); u++) {
            if (cor[u] == BRANCO) { // se o vértice não foi visitado
                dfsVisit(u); // visita o vértice
            }
        }
    }

    // método recursivo para visitar os vértices do grafo
    private void dfsVisit(int u) {
        tempo++; // incrementa o tempo
        tempo_descoberta[u] = tempo; // marca o tempo de descoberta do vértice u
        cor[u] = CINZENTO; // marca o vértice como em processo de visitação
        for (int v : grafo.getListaAdj()[u]) { // percorre todos os vértices adjacentes a u
            if (cor[v] == BRANCO) { // se o vértice v não foi visitado
                pais[v] = u; // define u como pai de v
                dfsVisit(v); // visita recursivamente o vértice v
            } else if (cor[v] == CINZENTO && pais[u] != v) { // se v está em processo de visitação e não é pai de u
                arestasDeRetorno.add((u + 1) + " -> " + (v + 1)); // aresta de retorno encontrada
            }
        }
        cor[u] = PRETO; // marca o vértice como completamente visitado
        tempo++; // incrementa o tempo
        tempo_finalizacao[u] = tempo; // marca o tempo de finalização do vértice u
    }

    // método para retornar o número de arestas entre dois vértices
    public int getNumeroDeArestas(int origem, int destino) {
        boolean[] visitado = new boolean[grafo.getVertices()]; // inicializa o array de vértices visitados
        return dfsNumeroDeArestasUtil(origem, destino, visitado, 0); // chama o método utilitário recursivo
    }

    // método recursivo para contar o número de arestas entre dois vértices
    private int dfsNumeroDeArestasUtil(int atual, int destino, boolean[] visitado, int numArestas) {
        if (atual == destino) { // se o vértice atual é o destino
            return numArestas; // retorna o número de arestas
        }
        visitado[atual] = true; // marca o vértice atual como visitado
        for (int vizinho : grafo.getListaAdj()[atual]) { // percorre todos os vértices adjacentes ao vértice atual
            if (!visitado[vizinho]) { // se o vértice vizinho não foi visitado
                int resultado = dfsNumeroDeArestasUtil(vizinho, destino, visitado, numArestas + 1); // chama o método recursivo
                if (resultado != -1) { // se encontrou o destino
                    return resultado; // retorna o resultado
                }
            }
        }
        return -1; // se não encontrar o destino, retorna -1
    }

    // método para retornar um caminho entre dois vértices
    public List<Integer> getCaminho(int origem, int destino) {
        boolean[] visitado = new boolean[grafo.getVertices()]; // inicializa o array de vértices visitados
        List<Integer> caminho = new ArrayList<>(); // inicializa a lista do caminho
        if (dfsCaminhoUtil(origem, destino, visitado, caminho)) { // chama o método utilitário recursivo
            return caminho; // retorna o caminho
        } else {
            return Collections.emptyList(); // retorna lista vazia se não houver caminho
        }
    }

    // método recursivo para encontrar o caminho entre dois vértices
    private boolean dfsCaminhoUtil(int atual, int destino, boolean[] visitado, List<Integer> caminho) {
        visitado[atual] = true; // marca o vértice atual como visitado
        caminho.add(atual); // adiciona o vértice atual ao caminho

        if (atual == destino) { // se o vértice atual é o destino
            return true; // retorna verdadeiro
        }

        for (int vizinho : grafo.getListaAdj()[atual]) { // percorre todos os vértices adjacentes ao vértice atual
            if (!visitado[vizinho]) { // se o vértice vizinho não foi visitado
                if (dfsCaminhoUtil(vizinho, destino, visitado, caminho)) { // chama o método recursivo
                    return true; // retorna verdadeiro se encontrar o destino
                }
            }
        }

        caminho.remove(caminho.size() - 1); // backtrack para remover o último vértice adicionado
        return false; // retorna falso se não encontrar o destino
    }

    // método para retornar todas as arestas de retorno
    public List<String> getArestasDeRetorno() {
        return new ArrayList<>(arestasDeRetorno); // retorna uma cópia da lista de arestas de retorno
    }
}
