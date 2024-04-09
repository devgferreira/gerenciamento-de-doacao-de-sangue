package com.gabriel.ferreira.souto.msdoacao.infra.exceptions.handler;

import com.gabriel.ferreira.souto.msdoacao.domain.enums.ErrorCodes;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.DoacaoNaoEncontradoException;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.DoadorNaoEncontradoException;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.ExceptionResponse;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.PesoInvalidoException;
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

    @ExceptionHandler(DoacaoNaoEncontradoException.class)
    public final ResponseEntity<Object> handlerDoacaoNaoEncontradoException(DoacaoNaoEncontradoException ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.DOACAO_NAO_ENCONTRADA, ex.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
    @ExceptionHandler(PesoInvalidoException.class)
    public final ResponseEntity<Object> handlerPesoInvalidoException(PesoInvalidoException ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.PESO_INVALIDO, ex.getMessage());
        return  ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }

}
