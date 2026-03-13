package com.deloitte.projeto_estoque.service;

import com.deloitte.projeto_estoque.model.Produto;
import com.deloitte.projeto_estoque.validation.ProdutoValidation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoValidator {

    private final List<ProdutoValidation> validations;

    public ProdutoValidator(List<ProdutoValidation> validations) {
        this.validations = validations;
    }

    public void validar(Produto produto) {
        for (ProdutoValidation validation : validations) {
            validation.validar(produto);
        }
    }
}