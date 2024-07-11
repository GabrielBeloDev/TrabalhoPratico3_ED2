import java.util.*;

public class Grafo {
    private int vertices; // número de vértices no grafo
    private LinkedList<Integer>[] listaAdj; // lista de adjacências para armazenar as arestas

    // Construtor para inicializar o grafo
    public Grafo(int vertices) {
        this.vertices = vertices;
        listaAdj = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i) {
            listaAdj[i] = new LinkedList<>();
        }
    }

    // Método para adicionar uma aresta ao grafo
    public void adicionarAresta(int u, int v) {
        listaAdj[u].add(v);
    }

    // Retorna a lista de adjacências
    public LinkedList<Integer>[] getListaAdj() {
        return listaAdj;
    }

    // Retorna o número de vértices no grafo
    public int getVertices() {
        return vertices;
    }
}
