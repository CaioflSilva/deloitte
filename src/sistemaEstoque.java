import java.util.Scanner;

class sistemaEstoque {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Estoque estoque = new Estoque();

        int opcao;
        int id = 1;

        do {

            System.out.println("======= Sistema de Estoque =========");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Alterar Produto");
            System.out.println("4 - Remover Produto");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();  //  quebra de linha

            switch (opcao) {

                case 1:
                    // Cadastrar Produto
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Preço: ");
                    double preco = Double.parseDouble(scanner.nextLine());

                    System.out.print("Quantidade: ");
                    int quantidade = Integer.parseInt(scanner.nextLine());

                    Produto produto = new Produto(id, nome, preco, quantidade);
                    estoque.adicionarProduto(produto);
                    id++;

                    System.out.println("Produto cadastrado!");
                    break;

                case 2:
                    // Listar Produtos
                    estoque.listarProdutos();
                    break;

                case 3:
                    // Alterar Produto
                    System.out.print("Índice do produto: ");
                    int indice = scanner.nextInt();
                    scanner.nextLine();  // quebra de linha

                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Novo preço: ");
                    double novoPreco = Double.parseDouble(scanner.nextLine());

                    System.out.print("Nova quantidade: ");
                    int novaQuantidade = Integer.parseInt(scanner.nextLine());

                    estoque.alterarProduto(indice, novoNome, novoPreco, novaQuantidade);
                    break;

                case 4:
                    // Remover Produto
                    System.out.print("Índice para remover: ");
                    int indiceRemover = scanner.nextInt();
                    scanner.nextLine();  // quebra de linha

                    estoque.removerProduto(indiceRemover);
                    break;

                case 0:
                    // Sair do sistema
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}