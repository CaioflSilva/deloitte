package com.deloitte.projeto_estoque.console;

import com.deloitte.projeto_estoque.model.Produto;

import java.util.ArrayList;

public class Estoque {

    public ArrayList<Produto> produtos = new ArrayList<>();

    // Adicionar produto
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    // Listar produtos
    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Estoque vazio.");
        } else {
            for (int i = 0; i < produtos.size(); i++) {
                System.out.println(i + " - " + produtos.get(i));
            }
        }
    }

    // Remover produto
    public void removerProduto(int indice) {
        if (indice >= 0 && indice < produtos.size()) {
            produtos.remove(indice);
            System.out.println("Produto removido!");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    // Alterar produto
    public void alterarProduto(int indice, String novoNome, double novoPreco, int novaQuantidade) {
        if (indice >= 0 && indice < produtos.size()) {
            Produto produto = produtos.get(indice);
            produto.setNome(novoNome);
            produto.setPreco(novoPreco);
            produto.setQuantidade(novaQuantidade);
            System.out.println("Produto alterado com sucesso!");
        } else {
            System.out.println("Índice inválido para alteração.");
        }
    }
}