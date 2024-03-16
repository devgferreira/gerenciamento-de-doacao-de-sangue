package com.gabriel.ferreira.souto.msdoador.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TipoSanguineoInvalidoException extends RuntimeException{
    private ExceptionResponse exceptionResponse;

    public TipoSanguineoInvalidoException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
