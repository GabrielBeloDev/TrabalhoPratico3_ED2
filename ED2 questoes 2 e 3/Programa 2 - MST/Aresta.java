public class Aresta<T> implements Comparable<Aresta<T>> {
    private T origem;
    private T destino;
    private int peso;

    public Aresta(T origem, T destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public T getOrigem() {
        return origem;
    }

    public T getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public int compareTo(Aresta<T> outra) {
        return Integer.compare(outra.getPeso(), this.peso); // Ordem decrescente
    }
}
