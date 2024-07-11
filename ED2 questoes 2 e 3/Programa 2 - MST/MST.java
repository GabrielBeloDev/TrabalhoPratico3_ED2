import java.util.*;

public class MST<T> {
    public List<Aresta<T>> encontrarMST(Grafo<T> grafo) {
        List<Aresta<T>> arestas = grafo.getArestas();
        Set<T> vertices = grafo.getVertices();

        // Ordena as arestas em ordem crescente
        Collections.sort(arestas);

        UnionFind<T> uf = new UnionFind<>(vertices);

        // Lista para armazenar as arestas da MST
        List<Aresta<T>> mst = new ArrayList<>();

        for (Aresta<T> aresta : arestas) {
            T origem = aresta.getOrigem();
            T destino = aresta.getDestino();

            // Verifica se a aresta conecta dois componentes diferentes
            if (!uf.conectados(origem, destino)) {
                uf.unir(origem, destino);
                mst.add(aresta);
            }
        }

        return mst;
    }
}
