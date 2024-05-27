package com.gabriel.ferreira.souto.msdoador.infra.exceptions.handler;

import com.gabriel.ferreira.souto.msdoador.domain.enums.ErrorCodes;
import com.gabriel.ferreira.souto.msdoador.infra.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DoadorNaoEncontradoException.class)
    public final ResponseEntity<Object> handlerDoadorNaoEncontradoException(DoadorNaoEncontradoException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.DOADOR_NAO_ENCONTRADO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(EnderecoNaoEncontradoException.class)
    public final ResponseEntity<Object> handlerEnderecoNaoEncontradoException(EnderecoNaoEncontradoException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.ENDERECO_NAO_ENCONTRADO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(EmailJaExisteException.class)
    public final ResponseEntity<Object> handlerEmailJaExisteException(EmailJaExisteException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.EMAIL_JA_EXISTE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }

    @ExceptionHandler(EnderecoInvalidoException.class)
    public final ResponseEntity<Object> handlerEnderecoInvalidoException(EnderecoInvalidoException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.ENDERECO_INVALIDO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }

    @ExceptionHandler(TipoSanguineoInvalidoException.class)
    public final ResponseEntity<Object> handlerTipoSanguineoInvalidoException(TipoSanguineoInvalidoException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.TIPO_SANGUINEO_INVALIDO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }

    @ExceptionHandler(DoadorInvalidoException.class)
    public final ResponseEntity<Object> handlerDoadorInvalidoException(DoadorInvalidoException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.DOADOR_INVALIDO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public final ResponseEntity<Object> handlerCpfInvalidoException(CpfInvalidoException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CPF_INVALIDO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }

    @ExceptionHandler(CpfJaExisteException.class)
    public final ResponseEntity<Object> handlerCpfJaExisteException(CpfJaExisteException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CPF_JA_EXISTE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(exceptionResponse);
    }
}
