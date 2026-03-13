package com.deloitte.projeto_estoque.repository;

import com.deloitte.projeto_estoque.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoRepositoryTest {

    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp() {
        produtoRepository = new ProdutoRepository();
    }

    @Test
    void deveSalvarProdutoComSucesso() {
        Produto produto = new Produto(1L, "Notebook", "Notebook Dell i5", 3500.0, 10);

        Produto resultado = produtoRepository.salvar(produto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Notebook", resultado.getNome());
        assertEquals("Notebook Dell i5", resultado.getDescricao());
        assertEquals(3500.0, resultado.getPreco());
        assertEquals(10, resultado.getQuantidade());
    }

    @Test
    void deveListarTodosProdutos() {
        Produto produto1 = new Produto(1L, "Notebook", "Notebook Dell", 3500.0, 10);
        Produto produto2 = new Produto(2L, "Mouse", "Mouse sem fio", 80.0, 20);

        produtoRepository.salvar(produto1);
        produtoRepository.salvar(produto2);

        List<Produto> produtos = produtoRepository.listarTodos();

        assertEquals(2, produtos.size());
        assertEquals("Notebook", produtos.get(0).getNome());
        assertEquals("Mouse", produtos.get(1).getNome());
    }

    @Test
    void deveBuscarProdutoPorIdComSucesso() {
        Produto produto = new Produto(1L, "Notebook", "Notebook Dell", 3500.0, 10);
        produtoRepository.salvar(produto);

        Produto resultado = produtoRepository.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Notebook", resultado.getNome());
    }

    @Test
    void deveRetornarNuloAoBuscarProdutoInexistente() {
        Produto resultado = produtoRepository.buscarPorId(99L);

        assertNull(resultado);
    }

    @Test
    void deveRetornarTrueQuandoProdutoExistir() {
        Produto produto = new Produto(1L, "Notebook", "Notebook Dell", 3500.0, 10);
        produtoRepository.salvar(produto);

        boolean existe = produtoRepository.existePorId(1L);

        assertTrue(existe);
    }

    @Test
    void deveRetornarFalseQuandoProdutoNaoExistir() {
        boolean existe = produtoRepository.existePorId(99L);

        assertFalse(existe);
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        Produto produto = new Produto(1L, "Notebook", "Notebook Dell", 3500.0, 10);
        produtoRepository.salvar(produto);

        Produto novoProduto = new Produto(1L, "Notebook Gamer", "RTX 4060", 5200.0, 5);

        Produto atualizado = produtoRepository.atualizar(1L, novoProduto);

        assertNotNull(atualizado);
        assertEquals("Notebook Gamer", atualizado.getNome());
        assertEquals("RTX 4060", atualizado.getDescricao());
        assertEquals(5200.0, atualizado.getPreco());
        assertEquals(5, atualizado.getQuantidade());
    }

    @Test
    void deveRemoverProdutoComSucesso() {
        Produto produto = new Produto(1L, "Notebook", "Notebook Dell", 3500.0, 10);
        produtoRepository.salvar(produto);

        produtoRepository.remover(1L);

        assertFalse(produtoRepository.existePorId(1L));
        assertNull(produtoRepository.buscarPorId(1L));
    }
}