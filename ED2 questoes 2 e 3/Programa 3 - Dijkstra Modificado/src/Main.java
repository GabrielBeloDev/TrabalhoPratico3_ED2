import java.util.List;
import java.util.ArrayList;

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

        DijkstraModificado<String> dijkstraMod = new DijkstraModificado<>();
        List<Caminho<String>> resultados = dijkstraMod.encontrarDoisMenoresCaminhos(grafo, "A", "E");

        System.out.println("Dois menores caminhos:");
        for (int i = 0; i < resultados.size(); i++) {
            Caminho<String> caminho = resultados.get(i);
            System.out.println("Caminho " + (i + 1) + ": " + caminho.getVertices() + " com peso total de " + caminho.getPesoTotal());
        }
    }
}
