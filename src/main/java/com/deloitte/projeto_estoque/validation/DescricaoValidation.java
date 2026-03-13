package com.deloitte.projeto_estoque.validation;

import com.deloitte.projeto_estoque.exception.ExcecaoPersonalizada;
import com.deloitte.projeto_estoque.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class DescricaoValidation implements ProdutoValidation {

    @Override
    public void validar(Produto produto) {
        if (produto.getDescricao() == null || produto.getDescricao().isBlank()) {
            throw new ExcecaoPersonalizada("Descrição inválida.");
        }
    }
}