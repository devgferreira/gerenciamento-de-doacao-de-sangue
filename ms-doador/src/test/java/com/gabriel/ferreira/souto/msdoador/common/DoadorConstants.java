package com.gabriel.ferreira.souto.msdoador.common;

import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorRequestDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorResponseDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.EnderecoDTO;
import com.gabriel.ferreira.souto.msdoador.domain.enums.Genero;
import com.gabriel.ferreira.souto.msdoador.domain.model.doador.Doador;

import java.util.Date;

public class DoadorConstants {
    public static final Doador DOADOR_VALIDO =
            new Doador("82116296072", "Gabriel", "gabrie@gmail.com",
            new Date(), Genero.M, 60, "B-");

    public static final DoadorRequestDTO DOADOR_REQUEST_DTO_VALIDO =
            new DoadorRequestDTO("82116296072", "Gabriel", "gabrie@gmail.com",
            new Date(), Genero.M, 60, "B-",
            new EnderecoDTO("Vila Maria", "SP", "SP", "02128020"));
    public static final DoadorRequestDTO DOADOR_REQUEST_DTO_COM_CPF_INVALIDO =
            new DoadorRequestDTO("821162960721", "Gabriel", "gabrie@gmail.com",
                    new Date(), Genero.M, 60, "B-",
                    new EnderecoDTO("Vila Maria", "SP", "SP", "02128020"));

    public static final DoadorRequestDTO DOADOR_REQUEST_DTO_INVALIDO =
            new DoadorRequestDTO("", "", "",
            null, null,null , "",
            new EnderecoDTO("", "", "", ""));

    public static final DoadorResponseDTO DOADOR_RESPONSE_DTO_VALIDO =
            new DoadorResponseDTO("82116296072", "Gabriel", "gabrie@gmail.com",
            new Date(), Genero.M, 60, "B-",
            new EnderecoDTO("Vila Maria", "SP", "SP", "02128020"));


}
