package com.gabriel.ferreira.souto.msdoacao.infra.exceptions;

import com.gabriel.ferreira.souto.msdoacao.domain.enums.ErrorCodes;
import lombok.Data;

@Data
public class ExceptionResponse {
    private final String code;
    private final String mensagem;

    public ExceptionResponse(final ErrorCodes errorCode, String details) {
        this.code = errorCode.name();
        this.mensagem = errorCode.getMensagem();
    }

}

