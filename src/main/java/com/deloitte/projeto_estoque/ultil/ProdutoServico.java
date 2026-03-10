package com.deloitte.projeto_estoque.ultil;

import com.deloitte.projeto_estoque.model.Produto;
import com.deloitte.projeto_estoque.exception.ExcecaoPersonalizada;

public class ProdutoServico {

       public Estoque estoque;


        public ProdutoServico(Estoque estoque) {
            this.estoque = estoque;
        }


        public void salvarProduto(Produto produto) {
            if (produto.getPreco() <= 0) {
                throw new ExcecaoPersonalizada("O preço do produto deve ser maior que zero.");
            }
            if (produto.getQuantidade() < 0) {
                throw new ExcecaoPersonalizada("A quantidade do produto não pode ser negativa.");
            }
            estoque.adicionarProduto(produto);
        }


        public void listarProdutos() {
            estoque.listarProdutos();
        }


        public void alterarProduto(int indice, String novoNome, double novoPreco, int novaQuantidade) {
            if (novoPreco <= 0) {
                throw new ExcecaoPersonalizada("O preço do produto deve ser maior que zero.");
            }
            if (novaQuantidade < 0) {
                throw new ExcecaoPersonalizada("A quantidade do produto não pode ser negativa.");
            }
            estoque.alterarProduto(indice, novoNome, novoPreco, novaQuantidade);
        }


        public void removerProduto(int indice) {
            estoque.removerProduto(indice);
        }

}
