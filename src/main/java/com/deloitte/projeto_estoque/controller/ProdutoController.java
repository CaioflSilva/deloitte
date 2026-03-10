package com.deloitte.projeto_estoque.controller;

import com.deloitte.projeto_estoque.model.Produto;
import com.deloitte.projeto_estoque.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto) {
        return produtoService.cadastrar(produto);
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Integer id) {
        return produtoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Integer id, @RequestBody Produto produto) {
        return produtoService.atualizar(id, produto);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Integer id) {
        produtoService.remover(id);
    }
}