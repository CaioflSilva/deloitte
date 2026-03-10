package com.deloitte.projeto_estoque.repository;

import com.deloitte.projeto_estoque.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProdutoRepository {

    private final Map<Integer, Produto> banco = new LinkedHashMap<>();

    public Produto salvar(Produto produto) {
        banco.put(produto.getId(), produto);
        return produto;
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(banco.values());
    }

    public Optional<Produto> buscarPorId(Integer id) {
        return Optional.ofNullable(banco.get(id));
    }

    public boolean existePorId(Integer id) {
        return banco.containsKey(id);
    }

    public void remover(Integer id) {
        banco.remove(id);
    }
}