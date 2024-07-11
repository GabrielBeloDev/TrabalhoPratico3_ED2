import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // lê o número de vértices do grafo
        System.out.print("Digite o número de vértices no grafo: ");
        int numVertices = scanner.nextInt();
        Grafo grafo = new Grafo(numVertices);

        // lê as arestas do grafo no formato 'origem destino' até que seja inserido '-1 -1'
        System.out.println("---------------");
        System.out.println("Comece com arestas maiores que 0! exemplo: 1, 2, 3");
        System.out.println("----------------");
        System.out.println("Digite as arestas do grafo no formato 'origem destino'. Digite '-1 -1' para parar.");
        while (true) {
            System.out.print("Aresta: ");
            int origem = scanner.nextInt() - 1; // ajuste para índice baseado em zero
            int destino = scanner.nextInt() - 1; // ajuste para índice baseado em zero
            if (origem == -2 && destino == -2) {
                break; // termina a leitura de arestas
            }
            if (origem < 0 || origem >= numVertices || destino < 0 || destino >= numVertices) {
                System.out.println("Vértices inválidos. Tente novamente."); // validação dos vértices
            } else {
                grafo.adicionarAresta(origem, destino);
                System.out.println("Aresta adicionada: " + (origem + 1) + " -> " + (destino + 1)); // ajuste para exibição
            }
        }

        // inicializa a DFS e executa a busca em profundidade
        System.out.println("Construção do grafo concluída.");
        DFS dfs = new DFS(grafo);
        dfs.dfs();

        // menu de opções para o usuário
        while (true) {
            exibirMenu();
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    // mostra o número de arestas entre dois vértices
                    System.out.print("Digite o vértice de origem: ");
                    int origemAresta = scanner.nextInt() - 1; // ajuste para índice baseado em zero
                    System.out.print("Digite o vértice de destino: ");
                    int destinoAresta = scanner.nextInt() - 1; // ajuste para índice baseado em zero
                    int numArestasEntreVertices = dfs.getNumeroDeArestas(origemAresta, destinoAresta);
                    if (numArestasEntreVertices == -1) {
                        System.out.println("Caminho não encontrado.");
                    } else {
                        System.out.println("Número de arestas de " + (origemAresta + 1) + " a " + (destinoAresta + 1) + ": " + numArestasEntreVertices); // ajuste para exibição
                    }
                    break;
                case 2:
                    // mostra o caminho entre dois vértices
                    System.out.print("Digite o vértice de origem: ");
                    int origemCaminho = scanner.nextInt() - 1; // ajuste para índice baseado em zero
                    System.out.print("Digite o vértice de destino: ");
                    int destinoCaminho = scanner.nextInt() - 1; // ajuste para índice baseado em zero
                    List<Integer> caminho = dfs.getCaminho(origemCaminho, destinoCaminho);
                    if (caminho.isEmpty()) {
                        System.out.println("Caminho não encontrado.");
                    } else {
                        System.out.print("Caminho de " + (origemCaminho + 1) + " a " + (destinoCaminho + 1) + ": ");
                        for (int vertice : caminho) {
                            System.out.print((vertice + 1) + " "); // ajuste para exibição
                        }
                        System.out.println();
                    }
                    break;
                case 3:
                    // mostra todas as arestas de retorno
                    List<String> arestasDeRetorno = dfs.getArestasDeRetorno();
                    if (arestasDeRetorno.isEmpty()) {
                        System.out.println("Nenhuma aresta de retorno encontrada.");
                    } else {
                        System.out.println("Arestas de retorno: " + arestasDeRetorno);
                    }
                    break;
                case 4:
                    // exibe o grafo atual
                    exibirGrafo(grafo);
                    break;
                case 5:
                    // sai do programa
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    // opção inválida
                    System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println();
        }
    }

    // método para exibir o menu de opções
    private static void exibirMenu() {
        System.out.println("----------------------------------------");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Mostrar o número de arestas entre dois vértices");
        System.out.println("2. Mostrar o caminho entre dois vértices");
        System.out.println("3. Mostrar todas as arestas do tipo retorno");
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
