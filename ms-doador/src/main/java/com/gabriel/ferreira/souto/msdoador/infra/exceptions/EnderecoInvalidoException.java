package com.gabriel.ferreira.souto.msdoador.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class EnderecoInvalidoException extends RuntimeException{
    private ExceptionResponse exceptionResponse;

    public EnderecoInvalidoException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
