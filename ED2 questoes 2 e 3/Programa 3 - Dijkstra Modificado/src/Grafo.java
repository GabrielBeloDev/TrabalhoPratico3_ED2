import java.util.*;

public class Grafo<T> {
    private Map<T, List<Aresta<T>>> adjacencia;

    public Grafo() {
        this.adjacencia = new HashMap<>();
    }

    public void adicionarAresta(T origem, T destino, int peso) {
        Aresta<T> aresta = new Aresta<>(origem, destino, peso);
        adjacencia.computeIfAbsent(origem, k -> new ArrayList<>()).add(aresta);
        adjacencia.computeIfAbsent(destino, k -> new ArrayList<>()); // Garante que o destino também seja adicionado
    }

    public List<Aresta<T>> getAdjacentes(T vertice) {
        return adjacencia.getOrDefault(vertice, new ArrayList<>());
    }

    public Set<T> getVertices() {
        return adjacencia.keySet();
    }
}
