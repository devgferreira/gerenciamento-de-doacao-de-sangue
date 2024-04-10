package com.gabriel.ferreira.souto.msdoacao.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    DOACAO_NAO_ENCONTRADA("Doação não encontrada."),
    IDADE_INVALIDA ("Idade inválida, idade miníma para doação é de 18 anos."),
    NAO_E_POSSIVEL_DOAR_M ("A Doação tem um tempo de espera de 90 em 90 dias para homens."),
    NAO_E_POSSIVEL_DOAR_F ("A Doação tem um tempo de espera de 60 em 60 dias para mulheres."),
    PESO_INVALIDO ("Peso inválido, minímo de 50kg para realizar a doação.");
    private final String mensagem;
}
