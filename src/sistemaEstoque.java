import java.util.ArrayList;
import java.util.Scanner;

public class sistemaEstoque {


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        ArrayList<String> estoque = new ArrayList<>();

        int opcao;
        int id = 1;

        do {


            System.out.println("=======   Sistema de Estoque  =========");

            System.out.println("1 Cadastrar Produtos ");
            System.out.println("2 Listar Produtos ");
            System.out.println("3 Alterar Produtos ");
            System.out.println("4 Remover Produtos ");
            System.out.println("0 Sair");
            System.out.println("Escolha");
            opcao = scanner.nextInt();
            scanner.nextLine();


            switch (opcao) {
                case 1:
                    System.out.println("Nome:");
                    String nome = scanner.nextLine();

                    System.out.println("Preço");
                    double preco = scanner.nextDouble();

                    System.out.println("Quantidade");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();

                    String produto = "ID" + id +
                            "|Nome: " + nome +
                            "| preço R$ " + preco +
                            "| Quantidade  " + quantidade;

                    estoque.add(produto);
                    id++;

                    System.out.println("Produto Cadastrado com sucesso");
                    break;

                case 2:
                    System.out.println("----- Lista De Produtos");

                    for (int i = 0; i < estoque.size(); i++) {
                        System.out.println(i + " - " + estoque.get(i));
                    }

                    break;

                case 3:
                    System.out.println("Digite o indice para Alterar");

                    int indiceAlterar = scanner.nextInt();
                    scanner.nextLine();

                    if (indiceAlterar >= 0 && indiceAlterar < estoque.size()) {
                        System.out.println("Nome novo");
                        String novoNome = scanner.nextLine();

                        System.out.println("Novo Preço");
                        double novoPreco = scanner.nextDouble();

                        System.out.println("Nova Quantidade");
                        int novaQuantidade = scanner.nextInt();
                        scanner.nextLine();

                        String novoProduto = "ID" + (indiceAlterar + 1) +

                                "|Nome" + novoNome +
                                "|Preço: R$ " + novoPreco +
                                "|Quantidade " + novaQuantidade;
                        estoque.set(indiceAlterar, novoProduto);

                        System.out.println("Produto atualizado!");
                    } else {
                        System.out.println("Indice invalido");
                    }
                    break;

                case 4:
                    System.out.println("Digite o indice para Remover");
                    int indiceRemover = scanner.nextInt();

                    if (indiceRemover >= 0 && indiceRemover < estoque.size()) {
                        estoque.remove((indiceRemover));
                        System.out.println("Produto Removido!");
                    } else {
                        System.out.println("Indice invalido");
                    }
                    break;

                case 0:
                    System.out.println("Encerrando Sistema");

                    break;

                default:
                    System.out.println("Opação invalida");

            }


        }
        while (opcao != 0);
        scanner.close();


    }
}



