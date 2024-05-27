package com.gabriel.ferreira.souto.msdoacao.infra.exceptions.handler;

import com.gabriel.ferreira.souto.msdoacao.domain.enums.ErrorCodes;
import com.gabriel.ferreira.souto.msdoacao.infra.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DoacaoNaoEncontradoException.class)
    public final ResponseEntity<Object> handlerDoacaoNaoEncontradoException(DoacaoNaoEncontradoException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.DOACAO_NAO_ENCONTRADA, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(PesoInvalidoException.class)
    public final ResponseEntity<Object> handlerPesoInvalidoException(PesoInvalidoException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.PESO_INVALIDO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }

    @ExceptionHandler(IdadeInvalidaException.class)
    public final ResponseEntity<Object> handlerIdadeInvalidaException(IdadeInvalidaException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.IDADE_INVALIDA, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }

    @ExceptionHandler(NaoEPossivelDoarMException.class)
    public final ResponseEntity<Object> handlerNaoEPossivelDoarException(NaoEPossivelDoarMException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.NAO_E_POSSIVEL_DOAR_M, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }

    @ExceptionHandler(NaoEPossivelDoarFException.class)
    public final ResponseEntity<Object> handlerNaoEPossivelDoarFException(NaoEPossivelDoarFException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.NAO_E_POSSIVEL_DOAR_F, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }

    @ExceptionHandler(QuantidadeDeSangueInvalidaException.class)
    public final ResponseEntity<Object> handlerQuantidadeDeSangueInvalidaException(QuantidadeDeSangueInvalidaException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.QUANTIDADE_DE_SANGUE_INVALIDA, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }

}
