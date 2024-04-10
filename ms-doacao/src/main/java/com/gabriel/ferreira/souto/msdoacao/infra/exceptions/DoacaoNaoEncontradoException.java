package com.gabriel.ferreira.souto.msdoacao.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DoacaoNaoEncontradoException extends RuntimeException {
    private ExceptionResponse exceptionResponse;

    public DoacaoNaoEncontradoException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
