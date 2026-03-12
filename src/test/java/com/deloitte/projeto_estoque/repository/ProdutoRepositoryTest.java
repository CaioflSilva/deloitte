package com.deloitte.projeto_estoque.repository;

import com.deloitte.projeto_estoque.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoRepositoryTest {

    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp() {
        produtoRepository = new ProdutoRepository();
    }

    @Test
    void deveSalvarProdutoComSucesso() {
        Produto produto = criarProduto(1, "Notebook", 3500.0, 10);

        Produto resultado = produtoRepository.salvar(produto);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Notebook", resultado.getNome());
        assertEquals(3500.0, resultado.getPreco());
        assertEquals(10, resultado.getQuantidade());
    }

    @Test
    void deveListarTodosProdutos() {
        Produto produto1 = criarProduto(1, "Notebook", 3500.0, 10);
        Produto produto2 = criarProduto(2, "Mouse", 80.0, 20);

        produtoRepository.salvar(produto1);
        produtoRepository.salvar(produto2);

        List<Produto> produtos = produtoRepository.listarTodos();

        assertEquals(2, produtos.size());
        assertEquals("Notebook", produtos.get(0).getNome());
        assertEquals("Mouse", produtos.get(1).getNome());
    }

    @Test
    void deveBuscarProdutoPorIdComSucesso() {
        Produto produto = criarProduto(1, "Notebook", 3500.0, 10);
        produtoRepository.salvar(produto);

        Optional<Produto> resultado = produtoRepository.buscarPorId(1);

        assertTrue(resultado.isPresent());
        assertEquals("Notebook", resultado.get().getNome());
    }

    @Test
    void deveRetornarVazioAoBuscarProdutoInexistente() {
        Optional<Produto> resultado = produtoRepository.buscarPorId(99);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveRetornarTrueQuandoProdutoExistir() {
        Produto produto = criarProduto(1, "Notebook", 3500.0, 10);
        produtoRepository.salvar(produto);

        boolean existe = produtoRepository.existePorId(1);

        assertTrue(existe);
    }

    @Test
    void deveRetornarFalseQuandoProdutoNaoExistir() {
        boolean existe = produtoRepository.existePorId(99);

        assertFalse(existe);
    }

    @Test
    void deveRemoverProdutoComSucesso() {
        Produto produto = criarProduto(1, "Notebook", 3500.0, 10);
        produtoRepository.salvar(produto);

        produtoRepository.remover(1);

        assertFalse(produtoRepository.existePorId(1));
        assertTrue(produtoRepository.buscarPorId(1).isEmpty());
    }

    private Produto criarProduto(Integer id, String nome, Double preco, Integer quantidade) {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setQuantidade(quantidade);
        return produto;
    }
}