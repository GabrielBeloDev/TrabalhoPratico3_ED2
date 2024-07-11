import java.util.*;

public class DijkstraModificado<T> {
    public List<Caminho<T>> encontrarDoisMenoresCaminhos(Grafo<T> grafo, T origem, T destino) {
        PriorityQueue<Caminho<T>> pq = new PriorityQueue<>();
        Caminho<T> caminhoInicial = new Caminho<>();
        caminhoInicial.adicionarVertice(origem, 0);
        pq.add(caminhoInicial);

        List<Caminho<T>> melhoresCaminhos = new ArrayList<>();
        Map<T, Integer> melhoresPesos = new HashMap<>();

        while (!pq.isEmpty() && melhoresCaminhos.size() < 2) {
            Caminho<T> caminhoAtual = pq.poll();
            List<T> verticesAtuais = caminhoAtual.getVertices();
            T ultimoVertice = verticesAtuais.get(verticesAtuais.size() - 1);

            if (ultimoVertice.equals(destino)) {
                melhoresCaminhos.add(caminhoAtual);
                continue;
            }

            for (Aresta<T> aresta : grafo.getAdjacentes(ultimoVertice)) {
                T proxVertice = aresta.getDestino();
                int novoPeso = caminhoAtual.getPesoTotal() + aresta.getPeso();

                if (melhoresPesos.containsKey(proxVertice) && novoPeso >= melhoresPesos.get(proxVertice) && verticesAtuais.contains(proxVertice)) {
                    continue;
                }

                Caminho<T> novoCaminho = new Caminho<>(caminhoAtual);
                novoCaminho.adicionarVertice(proxVertice, aresta.getPeso());
                pq.add(novoCaminho);
                melhoresPesos.put(proxVertice, novoPeso);
            }
        }

        return melhoresCaminhos;
    }
}
