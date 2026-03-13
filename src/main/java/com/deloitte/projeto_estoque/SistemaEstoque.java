package com.deloitte.projeto_estoque;

import com.deloitte.projeto_estoque.exception.ExcecaoPersonalizada;
import com.deloitte.projeto_estoque.exception.RecursoNaoEncontradoException;
import com.deloitte.projeto_estoque.model.Produto;
import com.deloitte.projeto_estoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SistemaEstoque {

    @Autowired
    public ProdutoService produtoService;

    public static void main(String[] args) {

        var context = SpringApplication.run(SistemaEstoque.class, args);
        SistemaEstoque sistema = context.getBean(SistemaEstoque.class);

        Scanner scanner = new Scanner(System.in);

        int opcao;
        Long id = 1L;

        do {
            System.out.println("======= Sistema de Estoque =========");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Alterar Produto");
            System.out.println("4 - Remover Produto");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {

                    case 1:
                        Produto produto = capturarDadosProduto(id, scanner);
                        sistema.produtoService.cadastrar(produto);
                        id++;
                        System.out.println("Produto cadastrado!");
                        break;

                    case 2:
                        sistema.produtoService.listarTodos().forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("Índice do produto: ");
                        Long indice = scanner.nextLong();
                        scanner.nextLine();

                        Produto novoProduto = capturarDadosProduto(indice, scanner);
                        sistema.produtoService.atualizar(indice, novoProduto);
                        System.out.println("Produto alterado!");
                        break;

                    case 4:
                        System.out.print("Índice para remover: ");
                        Long indiceRemover = scanner.nextLong();
                        scanner.nextLine();

                        sistema.produtoService.remover(indiceRemover);
                        System.out.println("Produto removido!");
                        break;

                    case 0:
                        System.out.println("Encerrando sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (ExcecaoPersonalizada | RecursoNaoEncontradoException e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static Produto capturarDadosProduto(Long id, Scanner scanner) {

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

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
                System.out.println("Preço inválido. Digite um número válido.");
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
                    System.out.println("Quantidade inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro.");
            }
        }

        return new Produto(id, nome, descricao, preco, quantidade);
    }
}