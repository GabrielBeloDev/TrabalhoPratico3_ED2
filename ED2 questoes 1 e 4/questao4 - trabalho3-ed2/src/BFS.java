import java.util.*;

public class BFS {
    private Grafo grafo; // grafo em que a BFS será executada
    private String[] cor; // array de cores para os vértices (BRANCO, CINZA, PRETO)
    private int[] distancia; // array de distâncias do vértice inicial para cada vértice
    private int[] predecessor; // array de predecessores dos vértices

    // construtor para inicializar os atributos da BFS
    public BFS(Grafo grafo) {
        this.grafo = grafo;
        cor = new String[grafo.getVertices()];
        distancia = new int[grafo.getVertices()];
        predecessor = new int[grafo.getVertices()];
    }

    // método para executar a BFS a partir de um vértice inicial
    public void bfs(int verticeInicial) {
        // inicializa as cores, distâncias e predecessores dos vértices
        for (int u = 0; u < grafo.getVertices(); u++) {
            cor[u] = "BRANCO"; // todos os vértices começam como não visitados
            distancia[u] = Integer.MAX_VALUE; // define a distância inicial como infinita
            predecessor[u] = -1; // define o predecessor inicial como inexistente
        }

        // define o vértice inicial como cinza, com distância 0 e sem predecessor
        cor[verticeInicial] = "CINZA"; // o vértice inicial é marcado como visitado
        distancia[verticeInicial] = 0; // a distância do vértice inicial para ele mesmo é 0
        predecessor[verticeInicial] = -1; // o vértice inicial não tem predecessor

        Queue<Integer> fila = new LinkedList<>(); // fila para manter os vértices a serem processados
        fila.add(verticeInicial); // adiciona o vértice inicial à fila

        // executa a BFS
        while (!fila.isEmpty()) {
            int u = fila.poll(); // remove o vértice da frente da fila
            // processa todos os vértices adjacentes a u
            for (int v : grafo.getListaAdj()[u]) {
                if (cor[v].equals("BRANCO")) { // se o vértice v não foi visitado
                    cor[v] = "CINZA"; // marca v como visitado
                    distancia[v] = distancia[u] + 1; // atualiza a distância de v
                    predecessor[v] = u; // define o predecessor de v
                    fila.add(v); // adiciona v à fila
                }
            }
            cor[u] = "PRETO"; // marca u como totalmente processado
        }
    }

    // método para retornar o número de arestas entre o vértice inicial e um vértice destino
    public int getNumeroDeArestas(int vertice) {
        if (distancia[vertice] == Integer.MAX_VALUE) {
            return -1; // Indica que não há caminho
        }
        return distancia[vertice];  // retorna a distância do vértice inicial até o vértice destino
    }

    // método para retornar o caminho do vértice inicial a um vértice destino
    public List<Integer> getCaminho(int vertice) {
        List<Integer> caminho = new LinkedList<>(); // lista para armazenar o caminho
        if (distancia[vertice] == Integer.MAX_VALUE) {
            return caminho; // retorna lista vazia indicando que não há caminho
        }
        // percorre o caminho do vértice destino até o vértice inicial
        for (int v = vertice; v != -1; v = predecessor[v]) {
            caminho.add(v); // adiciona o vértice ao caminho
        }
        Collections.reverse(caminho); // reverte a lista para obter o caminho do inicial ao destino
        return caminho;
    }

    // método para retornar todos os vértices que estão a uma dada distância do vértice inicial
    public List<Integer> getVerticesNaDistancia(int d) {
        List<Integer> verticesNaDistancia = new LinkedList<>(); // lista para armazenar os vértices
        // percorre todos os vértices e adiciona aqueles que estão a distância d
        for (int i = 0; i < distancia.length; i++) {
            if (distancia[i] == d) {
                verticesNaDistancia.add(i);
            }
        }
        return verticesNaDistancia;
    }
}
