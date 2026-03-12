package com.deloitte.projeto_estoque;

import com.deloitte.projeto_estoque.console.Estoque;
import com.deloitte.projeto_estoque.console.ProdutoServico;
import com.deloitte.projeto_estoque.exception.ExcecaoPersonalizada;
import com.deloitte.projeto_estoque.model.Produto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
@SpringBootApplication
 class SistemaEstoque {

    public static void main(String[] args) {
        SpringApplication.run(SistemaEstoque.class, args);
        Scanner scanner = new Scanner(System.in);
        Estoque estoque = new Estoque();
        ProdutoServico produtoServico = new ProdutoServico(estoque);

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
            scanner.nextLine();  // Quebra de linha

            try {
                switch (opcao) {

                    case 1:
                        // Cadastrar Produto
                        Produto produto = capturarDadosProduto(id, scanner);
                        produtoServico.salvarProduto(produto);
                        id++;
                        System.out.println("Produto cadastrado!");
                        break;

                    case 2:
                        // Listar Produtos
                        produtoServico.listarProdutos();
                        break;

                    case 3:
                        // Alterar Produto
                        System.out.print("Índice do produto: ");
                        int indice = scanner.nextInt();
                        scanner.nextLine();  // Quebra de linha

                        Produto novoProduto = capturarDadosProduto(indice, scanner);
                        produtoServico.alterarProduto(indice, novoProduto.getNome(), novoProduto.getPreco(), novoProduto.getQuantidade());
                        break;

                    case 4:
                        // Remover Produto
                        System.out.print("Índice para remover: ");
                        int indiceRemover = scanner.nextInt();
                        scanner.nextLine();  // Quebra de linha

                        produtoServico.removerProduto(indiceRemover);
                        break;

                    case 0:
                        // Sair do sistema
                        System.out.println("Encerrando sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (ExcecaoPersonalizada e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);

        scanner.close();
    }


    private static Produto capturarDadosProduto(int id, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        double preco = 0;
        boolean precoValido = false;
        while (!precoValido) {
            System.out.print("Preço: ");
            try {
                preco = Double.parseDouble(scanner.nextLine());
                if (preco > 0) {
                    precoValido = true;
                } else {
                    System.out.println("Preço inválido. O preço deve ser maior que zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Preço inválido. Por favor, insira um número válido.");
            }
        }

        int quantidade = 0;
        boolean quantidadeValida = false;
        while (!quantidadeValida) {
            System.out.print("Quantidade: ");
            try {
                quantidade = Integer.parseInt(scanner.nextLine());
                if (quantidade >= 0) {
                    quantidadeValida = true;
                } else {
                    System.out.println("Quantidade inválida. A quantidade não pode ser negativa.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Quantidade inválida. Por favor, insira um número inteiro.");
            }
        }

        return new Produto(id, nome, preco, quantidade);
    }
}