package com.gabriel.ferreira.souto.msdoador.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    DOADOR_NAO_ENCONTRADO("Doador não foi encontrado"),
    DOADOR_INVALIDO("Doador inválido, por favor, preencha todos os campos"),
    TIPO_SANGUINEO_INVALIDO("Tipo sanguineo inválido, por favor, coloque um tipo válido."),
    EMAIL_JA_EXISTE("Email já existe."),
    CPF_INVALIDO ("CPF Inválido."),
    CPF_JA_EXISTE ("CPF já existe."),
    ENDERECO_INVALIDO("Endereço inváilido, por favor, preencha todos os campos."),
    ENDERECO_NAO_ENCONTRADO("Endereço não foi encontrado");
    private final String mensagem;


}
