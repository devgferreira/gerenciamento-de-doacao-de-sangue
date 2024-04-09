package com.gabriel.ferreira.souto.msdoacao.infra.exceptions.constants;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PRIVATE)
public class ErrorConstants {
    public static final String DOACAO_NAO_ENCONTRADO = "Ms-Doacao - Doação não encontrado.";
    public static final String IDADE_INVALIDA = "Ms-Doacao - Idade inválida, idade miníma para doação é de 18 anos.";
    public static final String PESO_INVALIDO = "Ms-Doacao - Peso inválido, minímo de 50kg para realizar a doação.";
}
