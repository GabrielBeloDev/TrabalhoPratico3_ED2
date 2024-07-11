import java.util.ArrayList;
import java.util.List;

public class Caminho<T> implements Comparable<Caminho<T>> {
    private List<T> vertices;
    private int pesoTotal;

    public Caminho() {
        this.vertices = new ArrayList<>();
        this.pesoTotal = 0;
    }

    public Caminho(Caminho<T> outro) {
        this.vertices = new ArrayList<>(outro.vertices);
        this.pesoTotal = outro.pesoTotal;
    }

    public void adicionarVertice(T vertice, int peso) {
        vertices.add(vertice);
        pesoTotal += peso;
    }

    public List<T> getVertices() {
        return vertices;
    }

    public int getPesoTotal() {
        return pesoTotal;
    }

    @Override
    public int compareTo(Caminho<T> outro) {
        return Integer.compare(this.pesoTotal, outro.pesoTotal);
    }
}
