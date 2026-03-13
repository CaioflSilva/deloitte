package com.deloitte.projeto_estoque.validation;

import com.deloitte.projeto_estoque.exception.ExcecaoPersonalizada;
import com.deloitte.projeto_estoque.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class NomeValidation implements ProdutoValidation {

    @Override
    public void validar(Produto produto) {
        if (produto == null) {
            throw new ExcecaoPersonalizada("O produto não pode ser nulo.");
        }

        if (produto.getNome() == null || produto.getNome().isBlank() || produto.getNome().trim().length() < 2) {
            throw new ExcecaoPersonalizada("Nome inválido.");
        }
    }
}