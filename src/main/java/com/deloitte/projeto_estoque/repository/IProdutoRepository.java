package com.deloitte.projeto_estoque.repository;

import com.deloitte.projeto_estoque.model.Produto;
import java.util.List;

public interface IProdutoRepository {

    Produto salvar(Produto produto);

    List<Produto> listarTodos();

    Produto buscarPorId(Long id);

    Produto atualizar(Long id, Produto produto);

    void remover(Long id);

    boolean existePorId(Long id);
}