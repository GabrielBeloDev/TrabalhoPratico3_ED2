import java.util.*;

public class Grafo {
    private int vertices; // número de vértices no grafo
    private LinkedList<Integer> listaAdj[]; // lista de adjacências para representar o grafo

    // construtor para inicializar o grafo com um determinado número de vértices
    public Grafo(int vertices) {
        this.vertices = vertices;
        listaAdj = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i)
            listaAdj[i] = new LinkedList<>();
    }

    // método para adicionar uma aresta ao grafo
    public void adicionarAresta(int u, int v) {
        listaAdj[u].add(v); // adiciona o vértice w à lista de adjacências do vértice v
    }

    // método para obter a lista de adjacências
    public LinkedList<Integer>[] getListaAdj() {
        return listaAdj;
    }

    // método para obter o número de vértices
    public int getVertices() {
        return vertices;
    }
}
