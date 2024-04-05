package com.gabriel.ferreira.souto.msdoacao.infra.exceptions.handler;

import com.gabriel.ferreira.souto.msdoacao.domain.enums.ErrorCodes;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.DoacaoNaoEncontradoException;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.DoadorNaoEncontradoException;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.ExceptionResponse;
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
