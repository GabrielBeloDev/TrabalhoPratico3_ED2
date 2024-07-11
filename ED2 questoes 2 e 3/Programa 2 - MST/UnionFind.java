import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UnionFind<T> {
    private Map<T, T> pai;
    private Map<T, Integer> tamanho;

    public UnionFind(Set<T> elementos) {
        pai = new HashMap<>();
        tamanho = new HashMap<>();
        for (T elemento : elementos) {
            pai.put(elemento, elemento);
            tamanho.put(elemento, 1);
        }
    }

    public T encontrar(T elemento) {
        if (!pai.get(elemento).equals(elemento)) {
            pai.put(elemento, encontrar(pai.get(elemento)));
        }
        return pai.get(elemento);
    }

    public void unir(T elemento1, T elemento2) {
        T raiz1 = encontrar(elemento1);
        T raiz2 = encontrar(elemento2);
        if (!raiz1.equals(raiz2)) {
            if (tamanho.get(raiz1) < tamanho.get(raiz2)) {
                pai.put(raiz1, raiz2);
                tamanho.put(raiz2, tamanho.get(raiz2) + tamanho.get(raiz1));
            } else {
                pai.put(raiz2, raiz1);
                tamanho.put(raiz1, tamanho.get(raiz1) + tamanho.get(raiz2));
            }
        }
    }

    public boolean conectados(T elemento1, T elemento2) {
        return encontrar(elemento1).equals(encontrar(elemento2));
    }
}
