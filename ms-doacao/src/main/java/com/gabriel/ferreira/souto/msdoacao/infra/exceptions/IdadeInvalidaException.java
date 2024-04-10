package com.gabriel.ferreira.souto.msdoacao.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class IdadeInvalidaException extends RuntimeException {
    private ExceptionResponse exceptionResponse;

    public IdadeInvalidaException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
