package com.deloitte.projeto_estoque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExcecaoPersonalizada.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> tratarExcecaoPersonalizada(ExcecaoPersonalizada ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());
        return erro;
    }
}