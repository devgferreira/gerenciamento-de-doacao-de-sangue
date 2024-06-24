package com.gabriel.ferreira.souto.msdoador.common;

import com.gabriel.ferreira.souto.msdoador.application.dtos.EnderecoDTO;
import com.gabriel.ferreira.souto.msdoador.domain.model.endereco.Endereco;

public class EnderecoConstants {
    public static final EnderecoDTO ENDERECO_DTO_VALIDO = new EnderecoDTO("Vila Maria", "SP", "SP", "02128020");
    public static final Endereco ENDERECO_VALIDO = new Endereco("Vila Maria", "SP", "SP", "02128020", "82134196009");
}
