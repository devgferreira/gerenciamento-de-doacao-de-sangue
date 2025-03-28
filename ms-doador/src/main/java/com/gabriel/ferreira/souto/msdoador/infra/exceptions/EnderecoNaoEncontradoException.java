package com.gabriel.ferreira.souto.msdoador.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EnderecoNaoEncontradoException extends RuntimeException {
    private final ExceptionResponse exceptionResponse;

    public EnderecoNaoEncontradoException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
