package com.gabriel.ferreira.souto.msdoacao.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class NaoEPossivelDoarMException extends RuntimeException {
    private ExceptionResponse exceptionResponse;

    public NaoEPossivelDoarMException(ExceptionResponse exceptionResponse) {
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
