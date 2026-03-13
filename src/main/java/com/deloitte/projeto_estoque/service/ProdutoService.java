package com.deloitte.projeto_estoque.service;

import com.deloitte.projeto_estoque.exception.RecursoNaoEncontradoException;
import com.deloitte.projeto_estoque.model.Produto;
import com.deloitte.projeto_estoque.repository.IProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final IProdutoRepository produtoRepository;
    private final ProdutoValidator produtoValidator;

    public ProdutoService(IProdutoRepository produtoRepository, ProdutoValidator produtoValidator) {
        this.produtoRepository = produtoRepository;
        this.produtoValidator = produtoValidator;
    }

    public Produto cadastrar(Produto produto) {
        produtoValidator.validar(produto);
        return produtoRepository.salvar(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.listarTodos();
    }

    public Produto buscarPorId(Long id) {
        Produto produto = produtoRepository.buscarPorId(id);
        if (produto == null) {
            throw new RecursoNaoEncontradoException("Produto não encontrado.");
        }
        return produto;
    }

    public Produto atualizar(Long id, Produto produto) {
        if (!produtoRepository.existePorId(id)) {
            throw new RecursoNaoEncontradoException("Produto não encontrado.");
        }

        produtoValidator.validar(produto);
        return produtoRepository.atualizar(id, produto);
    }

    public void remover(Long id) {
        if (!produtoRepository.existePorId(id)) {
            throw new RecursoNaoEncontradoException("Produto não encontrado.");
        }

        produtoRepository.remover(id);
    }
}