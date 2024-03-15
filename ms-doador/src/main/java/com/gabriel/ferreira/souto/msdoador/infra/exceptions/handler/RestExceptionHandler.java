package com.gabriel.ferreira.souto.msdoador.infra.exceptions.handler;

import com.gabriel.ferreira.souto.msdoador.domain.enums.ErrorCodes;
import com.gabriel.ferreira.souto.msdoador.infra.exceptions.DoadorNaoEncontradoException;
import com.gabriel.ferreira.souto.msdoador.infra.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DoadorNaoEncontradoException.class)
    public final ResponseEntity<Object> handlerDoadorNaoEncontradoException(DoadorNaoEncontradoException ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.DOADOR_NAO_ENCONTRADO, ex.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
}
