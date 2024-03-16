package com.gabriel.ferreira.souto.msdoador.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DoadorInvalidoException extends RuntimeException{
    private ExceptionResponse exceptionResponse;

    public DoadorInvalidoException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
