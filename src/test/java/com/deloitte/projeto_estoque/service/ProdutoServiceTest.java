package com.deloitte.projeto_estoque.service;

import com.deloitte.projeto_estoque.exception.RecursoNaoEncontradoException;
import com.deloitte.projeto_estoque.model.Produto;
import com.deloitte.projeto_estoque.repository.IProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private IProdutoRepository produtoRepository;

    @Mock
    private ProdutoValidator produtoValidator;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void deveCadastrarProdutoComSucesso() {
        Produto produto = new Produto(1L, "Produto A", "Descricao A", 100.0, 10);

        when(produtoRepository.salvar(produto)).thenReturn(produto);

        Produto resultado = produtoService.cadastrar(produto);

        assertNotNull(resultado);
        assertEquals("Produto A", resultado.getNome());

        verify(produtoValidator, times(1)).validar(produto);
        verify(produtoRepository, times(1)).salvar(produto);
    }

    @Test
    void deveBuscarProdutoPorIdComSucesso() {
        Produto produto = new Produto(1L, "Produto A", "Descricao A", 100.0, 10);

        when(produtoRepository.buscarPorId(1L)).thenReturn(produto);

        Produto resultado = produtoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void deveLancarExcecaoQuandoBuscarProdutoInexistente() {
        when(produtoRepository.buscarPorId(99L)).thenReturn(null);

        assertThrows(RecursoNaoEncontradoException.class, () -> produtoService.buscarPorId(99L));
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        Produto produto = new Produto(1L, "Produto A", "Descricao A", 100.0, 10);

        when(produtoRepository.existePorId(1L)).thenReturn(true);
        when(produtoRepository.atualizar(1L, produto)).thenReturn(produto);

        Produto resultado = produtoService.atualizar(1L, produto);

        assertNotNull(resultado);
        verify(produtoValidator, times(1)).validar(produto);
        verify(produtoRepository, times(1)).atualizar(1L, produto);
    }

    @Test
    void deveLancarExcecaoQuandoAtualizarProdutoInexistente() {
        Produto produto = new Produto(1L, "Produto A", "Descricao A", 100.0, 10);

        when(produtoRepository.existePorId(1L)).thenReturn(false);

        assertThrows(RecursoNaoEncontradoException.class, () -> produtoService.atualizar(1L, produto));
        verify(produtoRepository, never()).atualizar(anyLong(), any());
    }

    @Test
    void deveRemoverProdutoComSucesso() {
        when(produtoRepository.existePorId(1L)).thenReturn(true);

        produtoService.remover(1L);

        verify(produtoRepository, times(1)).remover(1L);
    }

    @Test
    void deveLancarExcecaoQuandoRemoverProdutoInexistente() {
        when(produtoRepository.existePorId(1L)).thenReturn(false);

        assertThrows(RecursoNaoEncontradoException.class, () -> produtoService.remover(1L));
        verify(produtoRepository, never()).remover(anyLong());
    }
}