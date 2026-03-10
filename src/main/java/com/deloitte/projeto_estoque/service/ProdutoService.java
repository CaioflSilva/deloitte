package com.deloitte.projeto_estoque.service;

import com.deloitte.projeto_estoque.exception.ExcecaoPersonalizada;
import com.deloitte.projeto_estoque.model.Produto;
import com.deloitte.projeto_estoque.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final AtomicInteger sequenciaId = new AtomicInteger(1);

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrar(Produto produto) {
        validarProduto(produto);
        produto.setId(sequenciaId.getAndIncrement());
        return produtoRepository.salvar(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.listarTodos();
    }

    public Produto buscarPorId(Integer id) {
        return produtoRepository.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));
    }

    public Produto atualizar(Integer id, Produto produtoAtualizado) {
        validarProduto(produtoAtualizado);

        Produto produtoExistente = buscarPorId(id);
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());

        return produtoRepository.salvar(produtoExistente);
    }

    public void remover(Integer id) {
        if (!produtoRepository.existePorId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        produtoRepository.remover(id);
    }

    private void validarProduto(Produto produto) {
        if (produto == null) {
            throw new ExcecaoPersonalizada("O produto não pode ser nulo.");
        }

        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new ExcecaoPersonalizada("O nome do produto é obrigatório.");
        }

        if (produto.getPreco() == null || produto.getPreco() <= 0) {
            throw new ExcecaoPersonalizada("O preço do produto deve ser maior que zero.");
        }

        if (produto.getQuantidade() == null || produto.getQuantidade() < 0) {
            throw new ExcecaoPersonalizada("A quantidade do produto não pode ser negativa.");
        }
    }
}