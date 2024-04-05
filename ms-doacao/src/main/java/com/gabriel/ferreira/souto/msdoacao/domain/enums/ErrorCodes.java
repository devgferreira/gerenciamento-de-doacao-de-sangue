package com.gabriel.ferreira.souto.msdoacao.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    DOADOR_NAO_ENCONTRADO("Doador não foi encontrado"),
    DOACAO_NAO_ENCONTRADA("Doação não encontrada");
    private final String mensagem;
}
