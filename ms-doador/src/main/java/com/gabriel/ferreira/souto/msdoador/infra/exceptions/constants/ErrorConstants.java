package com.gabriel.ferreira.souto.msdoador.infra.exceptions.constants;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PRIVATE)
public class ErrorConstants {
    public static final String DOADOR_NAO_ENCONTRADO = " Ms-Doador - Doador não encontrado.";
    public static final String DOADOR_INVALIDO = " Ms-Doador - Doador inválido.";
    public static final String TIPO_SANGUINEO_INVALIDO = " Ms-Doador - Tipo sanguineo inválido.";
    public static final String EMAIL_JA_EXISTE = " Ms-Doador - Email já existe.";
    public static final String CPF_INVALIDO = " Ms-Doador - CPF Inválido.";
    public static final String CPF_JA_EXISTE = " Ms-Doador - CPF já existe.";
    public static final String ENDERECO_NAO_ENCONTRADO = " Ms-Doador - Endereço não encontrado.";
    public static final String ENDERECO_INVALIDO = " Ms-Doador - Endereço Inválido.";
}
