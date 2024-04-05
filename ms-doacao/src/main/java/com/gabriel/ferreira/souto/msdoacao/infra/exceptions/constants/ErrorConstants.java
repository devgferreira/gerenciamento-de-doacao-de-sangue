package com.gabriel.ferreira.souto.msdoacao.infra.exceptions.constants;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PRIVATE)
public class ErrorConstants {
    public static final String DOADOR_NAO_ENCONTRADO = "Ms-Doacao - Doador não encontrado";
    public static final String DOACAO_NAO_ENCONTRADO = "Ms-Doacao - Doação não encontrado";
}
