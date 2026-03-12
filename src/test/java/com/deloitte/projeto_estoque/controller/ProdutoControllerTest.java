package com.deloitte.projeto_estoque.controller;

import com.deloitte.projeto_estoque.model.Produto;
import com.deloitte.projeto_estoque.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
        Produto produto = criarProduto(1, "Notebook", 3500.0, 10);

        when(produtoService.cadastrar(produto)).thenReturn(produto);

        Produto resultado = produtoController.cadastrar(produto);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Notebook", resultado.getNome());
        assertEquals(3500.0, resultado.getPreco());
        assertEquals(10, resultado.getQuantidade());

        verify(produtoService, times(1)).cadastrar(produto);
    }

    @Test
    void deveListarTodosProdutos() {
        Produto produto1 = criarProduto(1, "Notebook", 3500.0, 10);
        Produto produto2 = criarProduto(2, "Mouse", 80.0, 20);

        when(produtoService.listarTodos()).thenReturn(List.of(produto1, produto2));

        List<Produto> resultado = produtoController.listarTodos();

        assertEquals(2, resultado.size());
        assertEquals("Notebook", resultado.get(0).getNome());
        assertEquals("Mouse", resultado.get(1).getNome());

        verify(produtoService, times(1)).listarTodos();
    }

    @Test
    void deveBuscarProdutoPorIdComSucesso() {
        Produto produto = criarProduto(1, "Notebook", 3500.0, 10);

        when(produtoService.buscarPorId(1)).thenReturn(produto);

        Produto resultado = produtoController.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Notebook", resultado.getNome());

        verify(produtoService, times(1)).buscarPorId(1);
    }

    @Test
    void deveRetornar404AoBuscarProdutoInexistente() {
        when(produtoService.buscarPorId(99))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));

        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> produtoController.buscarPorId(99)
        );

        assertEquals(404, excecao.getStatusCode().value());
        assertEquals("Produto não encontrado.", excecao.getReason());

        verify(produtoService, times(1)).buscarPorId(99);
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        Produto produtoAtualizado = criarProduto(1, "Notebook Gamer", 5500.0, 5);

        when(produtoService.atualizar(1, produtoAtualizado)).thenReturn(produtoAtualizado);

        Produto resultado = produtoController.atualizar(1, produtoAtualizado);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Notebook Gamer", resultado.getNome());
        assertEquals(5500.0, resultado.getPreco());
        assertEquals(5, resultado.getQuantidade());

        verify(produtoService, times(1)).atualizar(1, produtoAtualizado);
    }

    @Test
    void deveRemoverProdutoComSucesso() {
        doNothing().when(produtoService).remover(1);

        assertDoesNotThrow(() -> produtoController.remover(1));

        verify(produtoService, times(1)).remover(1);
    }

    @Test
    void deveRetornar404AoRemoverProdutoInexistente() {
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."))
                .when(produtoService).remover(99);

        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> produtoController.remover(99)
        );

        assertEquals(404, excecao.getStatusCode().value());
        assertEquals("Produto não encontrado.", excecao.getReason());

        verify(produtoService, times(1)).remover(99);
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