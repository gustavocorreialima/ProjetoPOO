import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ControleEstoque {
    private static final int CAPACIDADE_ESTOQUE = 100;
    private static Produto[] produtos = new Produto[CAPACIDADE_ESTOQUE];
    private static int totalProdutos = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n==================== MENU ====================");
            System.out.println("| 1. Adicionar Produto                        |");
            System.out.println("| 2. Remover Produto                          |");
            System.out.println("| 3. Consultar Produto                        |");
            System.out.println("| 4. Atualizar Produto                        |");
            System.out.println("| 5. Adicionar Categoria                      |");
            System.out.println("| 6. Mostrar Produtos por Categoria           |");
            System.out.println("| 7. Consultar Produtos por Faixa de Preço    |");
            System.out.println("| 8. Exibir Todo o Estoque                    |");
            System.out.println("| 9. Aplicar Desconto                         |");
            System.out.println("| 10. Remover Desconto                        |");
            System.out.println("| 11. Mostrar Produtos com Desconto           |");
            System.out.println("| 0. Sair                                     |");
            System.out.println("==============================================");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    removerProduto();
                    break;
                case 3:
                    consultarProduto();
                    break;
                case 4:
                    atualizarProduto();
                    break;
                case 5:
                    adicionarCategoria();
                    break;
                case 6:
                    mostrarProdutosPorCategoria();
                    break;
                case 7:
                    consultarPorFaixaPreco();
                    break;
                case 8:
                    exibirEstoque();
                    break;
                case 9:
                    aplicarDesconto();
                    break;
                case 10:
                    removerDesconto();
                    break;
                case 11:
                    mostrarProdutosComDesconto();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close(); // Fechar o scanner ao encerrar o programa
    }

    private static void adicionarProduto() {
        if (totalProdutos < CAPACIDADE_ESTOQUE) {
            System.out.print("Nome do produto: ");
            scanner.nextLine(); // Consumir a quebra de linha pendente
            String nome = scanner.nextLine();
            System.out.print("Quantidade: ");
            int quantidade = scanner.nextInt();
            System.out.print("Preço: ");
            double preco = scanner.nextDouble();
            System.out.print("Categoria: ");
            scanner.nextLine(); // Consumir a quebra de linha pendente
            String categoria = scanner.nextLine();

            Produto produto = new Produto(nome, quantidade, preco, categoria);
            produtos[totalProdutos] = produto;
            totalProdutos++;

            System.out.println("Produto adicionado com sucesso!");
        } else {
            System.out.println("Estoque cheio! Não é possível adicionar mais produtos.");
        }
    }

    private static void removerProduto() {
        if (totalProdutos > 0) {
            System.out.print("Nome do produto a ser removido: ");
            scanner.nextLine(); // Consumir a quebra de linha pendente
            String nome = scanner.nextLine();
            int indice = -1;
            for (int i = 0; i < totalProdutos; i++) {
                if (produtos[i].getNome().equalsIgnoreCase(nome)) {
                    indice = i;
                    break;
                }
            }
            if (indice != -1) {
                for (int i = indice; i < totalProdutos - 1; i++) {
                    produtos[i] = produtos[i + 1];
                }
                totalProdutos--;
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Produto não encontrado no estoque.");
            }
        } else {
            System.out.println("Estoque vazio! Não há produtos para remover.");
        }
    }

    private static void consultarProduto() {
        if (totalProdutos > 0) {
            System.out.print("Nome do produto a ser consultado: ");
            scanner.nextLine(); // Consumir a quebra de linha pendente
            String nome = scanner.nextLine();
            for (int i = 0; i < totalProdutos; i++) {
                if (produtos[i].getNome().equalsIgnoreCase(nome)) {
                    System.out.println("Produto: " + produtos[i].getNome());
                    System.out.println("Quantidade: " + produtos[i].getQuantidade());
                    System.out.println("Preço: " + produtos[i].getPreco());
                    System.out.println("Categoria: " + produtos[i].getCategoria());
                    return;
                }
            }
            System.out.println("Produto não encontrado no estoque.");
        } else {
            System.out.println("Estoque vazio! Não há produtos para consultar.");
        }
    }

    private static void atualizarProduto() {
        if (totalProdutos > 0) {
            System.out.print("Nome do produto a ser atualizado: ");
            scanner.nextLine(); // Consumir a quebra de linha pendente
            String nome = scanner.nextLine();
            for (int i = 0; i < totalProdutos; i++) {
                if (produtos[i].getNome().equalsIgnoreCase(nome)) {
                    System.out.print("Nova quantidade: ");
                    int novaQuantidade = scanner.nextInt();
                    System.out.print("Novo preço: ");
                    double novoPreco = scanner.nextDouble();
                    System.out.print("Nova categoria: ");
                    scanner.nextLine(); // Consumir a quebra de linha pendente
                    String novaCategoria = scanner.nextLine();

                    produtos[i].setQuantidade(novaQuantidade);
                    produtos[i].setPreco(novoPreco);
                    produtos[i].setCategoria(novaCategoria);

                    System.out.println("Produto atualizado com sucesso!");
                    return;
                }
            }
            System.out.println("Produto não encontrado no estoque.");
        } else {
            System.out.println("Estoque vazio! Não há produtos para atualizar.");
        }
    }

    private static void adicionarCategoria() {
        System.out.print("Nome da categoria a ser adicionada: ");
        scanner.nextLine(); // Consumir a quebra de linha pendente
        String novaCategoria = scanner.nextLine();
        System.out.println("Categoria adicionada com sucesso!");
    }

    private static void mostrarProdutosPorCategoria() {
        System.out.print("Digite o nome da categoria: ");
        scanner.nextLine(); // Consumir a quebra de linha pendente
        String categoria = scanner.nextLine();

        System.out.println("Produtos na categoria " + categoria + ": ");

        boolean encontrou = false;
        for (int i = 0; i < totalProdutos; i++) {
            if (produtos[i].getCategoria().equalsIgnoreCase(categoria)) {
                System.out.println("Produto: " + produtos[i].getNome());
                System.out.println("Quantidade: " + produtos[i].getQuantidade());
                System.out.println("Preço: " + produtos[i].getPreco());
                System.out.println("-----------------");
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum produto encontrado nesta categoria.");
        }
    }

    private static void consultarPorFaixaPreco() {
        System.out.print("Digite o preço mínimo: ");
        double precoMin = scanner.nextDouble();
        System.out.print("Digite o preço máximo: ");
        double precoMax = scanner.nextDouble();

        List<Produto> produtosNaFaixaPreco = new ArrayList<>();

        for (int i = 0; i < totalProdutos; i++) {
            if (produtos[i].getPreco() >= precoMin && produtos[i].getPreco() <= precoMax) {
                produtosNaFaixaPreco.add(produtos[i]);
            }
        }

        if (!produtosNaFaixaPreco.isEmpty()) {
            // Ordena a lista de produtos pela ordem de preço do maior para o menor
            Collections.sort(produtosNaFaixaPreco, Comparator.comparingDouble(Produto::getPreco).reversed());

            System.out.println("Produtos na faixa de preço de " + precoMin + " a " + precoMax + " (do maior para o menor preço):");
            for (Produto produto : produtosNaFaixaPreco) {
                System.out.println("Produto: " + produto.getNome());
                System.out.println("Quantidade: " + produto.getQuantidade());
                System.out.println("Preço: " + produto.getPreco());
                System.out.println("Categoria: " + produto.getCategoria());
            }
        } else {
            System.out.println("Nenhum produto encontrado dentro da faixa de preço especificada.");
        }
    }

    private static void aplicarDesconto() {
        if (totalProdutos > 0) {
            System.out.print("Nome do produto para aplicar desconto: ");
            scanner.nextLine();
            String nome = scanner.nextLine();
            for (int i = 0; i < totalProdutos; i++) {
                if (produtos[i].getNome().equalsIgnoreCase(nome)) {
                    System.out.print("Porcentagem de desconto (%): ");
                    double percentualDesconto = scanner.nextDouble();
                    double desconto = (percentualDesconto / 100) * produtos[i].getPreco();
                    produtos[i].setDesconto(desconto);
                    System.out.println("Desconto aplicado com sucesso!");
                    return;
                }
            }
            System.out.println("Produto não encontrado no estoque.");
        } else {
            System.out.println("Estoque vazio! Não há produtos para aplicar desconto.");
        }
    }

    private static void removerDesconto() {
        if (totalProdutos > 0) {
            System.out.print("Nome do produto para remover desconto: ");
            scanner.nextLine();
            String nome = scanner.nextLine();
            for (int i = 0; i < totalProdutos; i++) {
                if (produtos[i].getNome().equalsIgnoreCase(nome)) {
                    produtos[i].setDesconto(0); // Remover o desconto
                    System.out.println("Desconto removido com sucesso!");
                    return;
                }
            }
            System.out.println("Produto não encontrado no estoque.");
        } else {
            System.out.println("Estoque vazio! Não há produtos para remover desconto.");
        }
    }

    private static void exibirEstoque() {
        if (totalProdutos > 0) {
            System.out.println("==== ESTOQUE ====");
            for (int i = 0; i < totalProdutos; i++) {
                System.out.println("Produto: " + produtos[i].getNome());
                System.out.println("Quantidade: " + produtos[i].getQuantidade());
                System.out.println("Preço: " + produtos[i].getPreco());
                System.out.println("Categoria: " + produtos[i].getCategoria());
                System.out.println("Desconto: " + produtos[i].getDesconto());
                System.out.println("-----------------");
            }
        } else {
            System.out.println("Estoque vazio! Não há produtos para exibir.");
        }
    }

    private static void mostrarProdutosComDesconto() {
    boolean encontrouProdutoComDesconto = false;
    System.out.println("Produtos com desconto:");
    for (int i = 0; i < totalProdutos; i++) {
        if (produtos[i].getDesconto() > 0) {
            encontrouProdutoComDesconto = true;
            System.out.println("Produto: " + produtos[i].getNome());
            System.out.println("Quantidade: " + produtos[i].getQuantidade());
            System.out.println("Preço original: " + (produtos[i].getPreco() + produtos[i].getDesconto())); // Exibe o preço original
            System.out.println("Desconto aplicado: " + produtos[i].getDesconto());
            System.out.println("Preço com desconto: " + produtos[i].getPreco()); // Exibe o preço com desconto
            System.out.println("Categoria: " + produtos[i].getCategoria());
            System.out.println("-----------------");
        }
    }
    if (!encontrouProdutoComDesconto) {
        System.out.println("Nenhum produto com desconto encontrado.");
    }
}


    // Classe interna para representar um produto
    static class Produto {
        private String nome;
        private int quantidade;
        private double preco;
        private double desconto;
        private String categoria;

        public Produto(String nome, int quantidade, double preco, String categoria) {
            this.nome = nome;
            this.quantidade = quantidade;
            this.preco = preco;
            this.categoria = categoria;
            this.desconto = 0; // Inicialmente nenhum desconto aplicado
        }

        public String getNome() {
            return nome;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public double getPreco() {
            // Retorna o preço com o desconto aplicado
            return preco - desconto;
        }

        public String getCategoria() {
            return categoria;
        }

        public double getDesconto() {
            return desconto;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }

        public void setPreco(double preco) {
            this.preco = preco;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public void setDesconto(double desconto) {
            this.desconto = desconto;
        }
    }
}