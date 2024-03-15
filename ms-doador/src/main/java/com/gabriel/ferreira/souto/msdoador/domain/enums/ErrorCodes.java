package com.gabriel.ferreira.souto.msdoador.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    DOADOR_NAO_ENCONTRADO("Doador não foi encontrado"),
    ENDERECO_NAO_ENCONTRADO("Endereço não foi encontrado");
    private final String mensagem;

}
