import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // solicita ao usuário o número de vértices no grafo
        System.out.print("Digite o número de vértices no grafo: ");
        int numVertices = scanner.nextInt();
        Grafo grafo = new Grafo(numVertices);

        // solicita ao usuário para digitar as arestas do grafo
        System.out.println("----------------------------");
        System.out.println("O número de vértices deve começar com 1");
        System.out.println("----------------------------");
        System.out.println("Digite as arestas do grafo no formato 'origem destino'. Digite '-1 -1' para parar.");

        // laço para entrada das arestas
        while (true) {
            System.out.print("Aresta: ");
            int origem = scanner.nextInt();
            int destino = scanner.nextInt();
            // condição de parada
            if (origem == -1 && destino == -1) {
                break;
            }
            // verifica se os vértices são válidos
            if (origem < 1 || origem > numVertices || destino < 1 || destino > numVertices) {
                System.out.println("Vértices inválidos. Tente novamente.");
            } else {
                // adiciona a aresta ao grafo
                grafo.adicionarAresta(origem - 1, destino - 1); // ajuste para índice baseado em zero
                System.out.println("Aresta adicionada: " + origem + " -> " + destino); // ajuste para exibição
            }
        }

        // solicita ao usuário para digitar o vértice inicial para a BFS
        System.out.print("Digite o vértice inicial para a BFS: ");
        int verticeInicial = scanner.nextInt();

        // executa a BFS a partir do vértice inicial
        BFS bfs = new BFS(grafo);
        bfs.bfs(verticeInicial - 1); // ajuste para índice baseado em zero

        boolean continuar = true;
        while (continuar) {
            exibirMenu(); // exibe o menu de opções
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();

            // executa a opção selecionada pelo usuário
            switch (opcao) {
                case 1 -> {
                    // mostra o número de arestas entre dois vértices
                    System.out.print("Digite o vértice de destino: ");
                    int destinoArestas = scanner.nextInt();
                    int resultado = bfs.getNumeroDeArestas(destinoArestas - 1); // ajuste para índice baseado em zero
                    if (resultado == -1) {
                        System.out.println("Caminho não encontrado.");
                    } else {
                        System.out.println("Número de arestas de " + verticeInicial + " a " + destinoArestas + ": " + resultado); // ajuste para exibição
                    }
                }
                case 2 -> {
                    // mostra o caminho entre dois vértices
                    System.out.print("Digite o vértice de destino: ");
                    int destinoCaminho = scanner.nextInt();
                    List<Integer> caminho = bfs.getCaminho(destinoCaminho - 1); // ajuste para índice baseado em zero
                    if (caminho.isEmpty()) {
                        System.out.println("Caminho não encontrado.");
                    } else {
                        System.out.print("Caminho de " + verticeInicial + " a " + destinoCaminho + ": ");
                        for (int vertice : caminho) {
                            System.out.print((vertice + 1) + " "); // ajuste para exibição
                        }
                        System.out.println();
                    }
                }
                case 3 -> {
                    // mostra todos os vértices a uma dada distância
                    System.out.print("Digite a distância: ");
                    int distancia = scanner.nextInt();
                    List<Integer> verticesNaDistancia = bfs.getVerticesNaDistancia(distancia);
                    if (verticesNaDistancia.isEmpty()) {
                        System.out.println("Nenhum vértice encontrado na distância especificada.");
                    } else {
                        System.out.print("Vértices a distância " + distancia + ": ");
                        for (int vertice : verticesNaDistancia) {
                            System.out.print((vertice + 1) + " "); // ajuste para exibição
                        }
                        System.out.println();
                    }
                }
                case 4 -> exibirGrafo(grafo); // exibe o grafo atual
                case 5 -> {
                    // encerra o programa
                    System.out.println("Saindo...");
                    continuar = false;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println();
        }
        scanner.close();
    }

    // método para exibir o menu de opções
    private static void exibirMenu() {
        System.out.println("----------------------------------------");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Mostrar o número de arestas entre dois vértices");
        System.out.println("2. Mostrar o caminho entre dois vértices");
        System.out.println("3. Mostrar todos os vértices a uma dada distância");
        System.out.println("4. Exibir o grafo atual");
        System.out.println("5. Sair");
    }

    // método para exibir o grafo atual
    private static void exibirGrafo(Grafo grafo) {
        System.out.println("Grafo atual:");
        for (int i = 0; i < grafo.getVertices(); i++) {
            System.out.print((i + 1) + " -> "); // ajuste para exibição
            for (int adj : grafo.getListaAdj()[i]) {
                System.out.print((adj + 1) + " "); // ajuste para exibição
            }
            System.out.println();
        }
    }
}
