import java.util.List;

public class Main {
    public static void main(String[] args) {
        Grafo<String> grafo = new Grafo<>();
        grafo.adicionarAresta("A", "B", 1);
        grafo.adicionarAresta("A", "C", 4);
        grafo.adicionarAresta("B", "C", 2);
        grafo.adicionarAresta("B", "D", 5);
        grafo.adicionarAresta("C", "D", 1);
        grafo.adicionarAresta("C", "E", 3);
        grafo.adicionarAresta("D", "E", 2);

        MST<String> mst = new MST<>();
        List<Aresta<String>> resultadoMST = mst.encontrarMST(grafo);

        System.out.println("Arestas na MST:");
        for (Aresta<String> aresta : resultadoMST) {
            System.out.println(aresta.getOrigem() + " - " + aresta.getDestino() + ": " + aresta.getPeso());
        }
    }
}
