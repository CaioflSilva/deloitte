package com.deloitte.projeto_estoque.repository;

import com.deloitte.projeto_estoque.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoRepository implements IProdutoRepository {

    private final List<Produto> produtos = new ArrayList<>();

    @Override
    public Produto salvar(Produto produto) {
        produtos.add(produto);
        return produto;
    }

    @Override
    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    @Override
    public Produto buscarPorId(Long id) {
        for (Produto produto : produtos) {
            if (produto.getId().equals(id)) {
                return produto;
            }
        }
        return null;
    }

    @Override
    public Produto atualizar(Long id, Produto novoProduto) {
        for (Produto produto : produtos) {
            if (produto.getId().equals(id)) {
                produto.setNome(novoProduto.getNome());
                produto.setDescricao(novoProduto.getDescricao());
                produto.setPreco(novoProduto.getPreco());
                produto.setQuantidade(novoProduto.getQuantidade());
                return produto;
            }
        }
        return null;
    }

    @Override
    public void remover(Long id) {
        produtos.removeIf(p -> p.getId().equals(id));
    }

    @Override
    public boolean existePorId(Long id) {
        return buscarPorId(id) != null;
    }
}