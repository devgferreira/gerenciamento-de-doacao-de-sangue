package com.gabriel.ferreira.souto.msdoador.infra.exceptions.constants;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PRIVATE)
public class ErrorConstants {
    public static final String DOADOR_NAO_ENCONTRADO = " Ms-Doador - Doador não encontrado.";
    public static final String ENDERECO_NAO_ENCONTRADO = " Ms-Doador - Endereço não encontrado.";
    public static final String EMAIL_JA_EXISTE = " Ms-Doador - Email já existe.";
}
