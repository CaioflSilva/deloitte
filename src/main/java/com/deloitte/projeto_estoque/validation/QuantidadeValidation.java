package com.deloitte.projeto_estoque.validation;

import com.deloitte.projeto_estoque.exception.ExcecaoPersonalizada;
import com.deloitte.projeto_estoque.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class QuantidadeValidation implements ProdutoValidation {

    @Override
    public void validar(Produto produto) {
        if (produto == null) {
            throw new ExcecaoPersonalizada("O produto não pode ser nulo.");
        }

        if (produto.getQuantidade() == null || produto.getQuantidade() < 0) {
            throw new ExcecaoPersonalizada("Quantidade inválida.");
        }
    }
}