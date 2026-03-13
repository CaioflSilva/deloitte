package com.deloitte.projeto_estoque.validation;

import com.deloitte.projeto_estoque.exception.ExcecaoPersonalizada;
import com.deloitte.projeto_estoque.model.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DescricaoValidationTest {

    @Test
    void deveLancarExcecaoQuandoDescricaoForVazia() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Notebook");
        produto.setDescricao("");
        produto.setPreco(3500.0);
        produto.setQuantidade(10);

        DescricaoValidation validation = new DescricaoValidation();

        assertThrows(ExcecaoPersonalizada.class, () -> validation.validar(produto));
    }

    @Test
    void naoDeveLancarExcecaoQuandoDescricaoForValida() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Notebook");
        produto.setDescricao("Notebook Dell i5 16GB SSD 512GB");
        produto.setPreco(3500.0);
        produto.setQuantidade(10);

        DescricaoValidation validation = new DescricaoValidation();

        assertDoesNotThrow(() -> validation.validar(produto));
    }
}