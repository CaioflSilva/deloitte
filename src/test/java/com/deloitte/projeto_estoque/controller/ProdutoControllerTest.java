package com.deloitte.projeto_estoque.controller;

import com.deloitte.projeto_estoque.model.Produto;
import com.deloitte.projeto_estoque.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    @Test
    void deveCadastrarProdutoComSucesso() {
        Produto produto = new Produto(1L, "Notebook", "Notebook Dell", 3500.0, 10);

        when(produtoService.cadastrar(produto)).thenReturn(produto);

        Produto resultado = produtoController.cadastrar(produto);

        assertNotNull(resultado);
        assertEquals("Notebook", resultado.getNome());
        verify(produtoService, times(1)).cadastrar(produto);
    }

    @Test
    void deveListarTodosProdutos() {
        Produto produto1 = new Produto(1L, "Notebook", "Notebook Dell", 3500.0, 10);
        Produto produto2 = new Produto(2L, "Mouse", "Mouse sem fio", 80.0, 20);

        when(produtoService.listarTodos()).thenReturn(List.of(produto1, produto2));

        List<Produto> produtos = produtoController.listarTodos();

        assertEquals(2, produtos.size());
        verify(produtoService, times(1)).listarTodos();
    }

    @Test
    void deveBuscarProdutoPorIdComSucesso() {
        Produto produto = new Produto(1L, "Notebook", "Notebook Dell", 3500.0, 10);

        when(produtoService.buscarPorId(1L)).thenReturn(produto);

        Produto resultado = produtoController.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(produtoService, times(1)).buscarPorId(1L);
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        Produto produto = new Produto(1L, "Notebook Gamer", "RTX 4060", 5200.0, 5);

        when(produtoService.atualizar(1L, produto)).thenReturn(produto);

        Produto resultado = produtoController.atualizar(1L, produto);

        assertNotNull(resultado);
        assertEquals("Notebook Gamer", resultado.getNome());
        verify(produtoService, times(1)).atualizar(1L, produto);
    }

    @Test
    void deveRemoverProdutoComSucesso() {
        doNothing().when(produtoService).remover(1L);

        assertDoesNotThrow(() -> produtoController.remover(1L));
        verify(produtoService, times(1)).remover(1L);
    }
}