package com.gabriel.ferreira.souto.msdoacao.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    DOACAO_NAO_ENCONTRADA("Doação não encontrada."),
    IDADE_INVALIDA ("Idade inválida, idade miníma para doação é de 18 anos."),
    PESO_INVALIDO ("Peso inválido, minímo de 50kg para realizar a doação.");
    private final String mensagem;
}
