package com.gabriel.ferreira.souto.msdoador.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    DOADOR_NAO_ENCONTRADO("Doador não foi encontrado"),
    ENDERECO_NAO_ENCONTRADO("Endereço não foi encontrado"),
    EMAIL_JA_EXISTE("Email já existe."),
    ENDERECO_INVALIDO("Endereço inváilido, por favor, preencha todos os campos."),
    TIPO_SANGUINEO_INVALIDO("Tipo sanguineo inválido, por favor, coloque um tipo válido."),
    EMAIL_NAO_ENCONTRADO("Email não encontrado.");
    private final String mensagem;


}
