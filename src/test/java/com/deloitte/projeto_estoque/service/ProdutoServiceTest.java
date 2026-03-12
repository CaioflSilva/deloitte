package com.deloitte.projeto_estoque.service;

import com.deloitte.projeto_estoque.exception.ExcecaoPersonalizada;
import com.deloitte.projeto_estoque.model.Produto;
import com.deloitte.projeto_estoque.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void deveCadastrarProdutoComSucesso() {
        Produto produto = criarProdutoValido();

        when(produtoRepository.salvar(any(Produto.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Produto resultado = produtoService.cadastrar(produto);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Notebook", resultado.getNome());
        assertEquals(3500.0, resultado.getPreco());
        assertEquals(10, resultado.getQuantidade());

        verify(produtoRepository, times(1)).salvar(any(Produto.class));
    }

    @Test
    void deveLancarExcecaoAoCadastrarProdutoNulo() {
        ExcecaoPersonalizada excecao = assertThrows(
                ExcecaoPersonalizada.class,
                () -> produtoService.cadastrar(null)
        );

        assertEquals("O produto não pode ser nulo.", excecao.getMessage());
        verify(produtoRepository, never()).salvar(any());
    }

    @Test
    void deveLancarExcecaoAoCadastrarProdutoSemNome() {
        Produto produto = criarProdutoValido();
        produto.setNome("");

        ExcecaoPersonalizada excecao = assertThrows(
                ExcecaoPersonalizada.class,
                () -> produtoService.cadastrar(produto)
        );

        assertEquals("O nome do produto é obrigatório.", excecao.getMessage());
        verify(produtoRepository, never()).salvar(any());
    }

    @Test
    void deveLancarExcecaoAoCadastrarProdutoComPrecoInvalido() {
        Produto produto = criarProdutoValido();
        produto.setPreco(0.0);

        ExcecaoPersonalizada excecao = assertThrows(
                ExcecaoPersonalizada.class,
                () -> produtoService.cadastrar(produto)
        );

        assertEquals("O preço do produto deve ser maior que zero.", excecao.getMessage());
        verify(produtoRepository, never()).salvar(any());
    }

    @Test
    void deveLancarExcecaoAoCadastrarProdutoComQuantidadeNegativa() {
        Produto produto = criarProdutoValido();
        produto.setQuantidade(-1);

        ExcecaoPersonalizada excecao = assertThrows(
                ExcecaoPersonalizada.class,
                () -> produtoService.cadastrar(produto)
        );

        assertEquals("A quantidade do produto não pode ser negativa.", excecao.getMessage());
        verify(produtoRepository, never()).salvar(any());
    }

    @Test
    void deveListarTodosProdutos() {
        Produto produto1 = criarProdutoValido();
        produto1.setId(1);

        Produto produto2 = new Produto();
        produto2.setId(2);
        produto2.setNome("Mouse");
        produto2.setPreco(80.0);
        produto2.setQuantidade(20);

        when(produtoRepository.listarTodos()).thenReturn(List.of(produto1, produto2));

        List<Produto> produtos = produtoService.listarTodos();

        assertEquals(2, produtos.size());
        assertEquals("Notebook", produtos.get(0).getNome());
        assertEquals("Mouse", produtos.get(1).getNome());

        verify(produtoRepository, times(1)).listarTodos();
    }

    @Test
    void deveBuscarProdutoPorIdComSucesso() {
        Produto produto = criarProdutoValido();
        produto.setId(1);

        when(produtoRepository.buscarPorId(1)).thenReturn(Optional.of(produto));

        Produto resultado = produtoService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Notebook", resultado.getNome());

        verify(produtoRepository, times(1)).buscarPorId(1);
    }

    @Test
    void deveLancar404AoBuscarProdutoInexistente() {
        when(produtoRepository.buscarPorId(99)).thenReturn(Optional.empty());

        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> produtoService.buscarPorId(99)
        );

        assertEquals(404, excecao.getStatusCode().value());
        assertEquals("Produto não encontrado.", excecao.getReason());

        verify(produtoRepository, times(1)).buscarPorId(99);
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        Produto produtoExistente = criarProdutoValido();
        produtoExistente.setId(1);

        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setNome("Notebook Gamer");
        produtoAtualizado.setPreco(5500.0);
        produtoAtualizado.setQuantidade(5);

        when(produtoRepository.buscarPorId(1)).thenReturn(Optional.of(produtoExistente));
        when(produtoRepository.salvar(any(Produto.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Produto resultado = produtoService.atualizar(1, produtoAtualizado);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Notebook Gamer", resultado.getNome());
        assertEquals(5500.0, resultado.getPreco());
        assertEquals(5, resultado.getQuantidade());

        verify(produtoRepository, times(1)).buscarPorId(1);
        verify(produtoRepository, times(1)).salvar(any(Produto.class));
    }

    @Test
    void deveLancar404AoRemoverProdutoInexistente() {
        when(produtoRepository.existePorId(99)).thenReturn(false);

        ResponseStatusException excecao = assertThrows(
                ResponseStatusException.class,
                () -> produtoService.remover(99)
        );

        assertEquals(404, excecao.getStatusCode().value());
        assertEquals("Produto não encontrado.", excecao.getReason());

        verify(produtoRepository, times(1)).existePorId(99);
        verify(produtoRepository, never()).remover(anyInt());
    }

    @Test
    void deveRemoverProdutoComSucesso() {
        when(produtoRepository.existePorId(1)).thenReturn(true);

        produtoService.remover(1);

        verify(produtoRepository, times(1)).existePorId(1);
        verify(produtoRepository, times(1)).remover(1);
    }

    private Produto criarProdutoValido() {
        Produto produto = new Produto();
        produto.setNome("Notebook");
        produto.setPreco(3500.0);
        produto.setQuantidade(10);
        return produto;
    }
}