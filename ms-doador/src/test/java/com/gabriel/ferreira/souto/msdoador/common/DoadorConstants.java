package com.gabriel.ferreira.souto.msdoador.common;

import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorRequestDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorResponseDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.EnderecoDTO;
import com.gabriel.ferreira.souto.msdoador.domain.enums.Genero;
import com.gabriel.ferreira.souto.msdoador.domain.model.doador.Doador;

import java.util.Calendar;
import java.util.Date;

public class DoadorConstants {
    public static final Doador DOADOR_VALIDO =
            new Doador("82116296072", "Gabriel", "gabrie@gmail.com",
            new Date(11, 4, 11, 18, 0), Genero.M, 60, "B-");
    public static final Doador DOADOR_VALIDO_2 =
            new Doador("82116296072", "Lucas", "lucas@gmail.com",
                    new Date(11, 4, 11, 18, 0), Genero.M, 80, "A+-");

    public static final DoadorRequestDTO DOADOR_REQUEST_DTO_VALIDO =
            new DoadorRequestDTO("82116296072", "Gabriel", "gabrie@gmail.com",
            new Date(11, 4, 11, 18, 0), Genero.M, 60, "B-",
            new EnderecoDTO("Vila Maria", "SP", "SP", "02128020"));

    public static final DoadorRequestDTO DOADOR_REQUEST_DTO_COM_ENDERECO_INVALIDO =
            new DoadorRequestDTO("82116296072", "Gabriel", "gabrie@gmail.com",
            new Date(11, 4, 11, 18, 0), Genero.M, 60, "B-",
            new EnderecoDTO("", "", "", ""));

    public static final DoadorRequestDTO DOADOR_REQUEST_DTO_COM_CPF_INVALIDO =
            new DoadorRequestDTO("8211629607", "Gabriel", "gabrie@gmail.com",
            new Date(11, 4, 11, 18, 0), Genero.M, 60, "B-",
            new EnderecoDTO("Vila Maria", "SP", "SP", "02128020"));

    public static final DoadorRequestDTO DOADOR_REQUEST_DTO_COM_TIPO_SANGUINEO_INVALIDO =
            new DoadorRequestDTO("82116296072", "Gabriel", "gabrie@gmail.com",
            new Date(11, 4, 11, 18, 0), Genero.M, 60, "C-",
            new EnderecoDTO("Vila Maria", "SP", "SP", "02128020"));

    public static final DoadorRequestDTO DOADOR_REQUEST_DTO_INVALIDO =
            new DoadorRequestDTO("", "", "",
            null, null,null , "",
            new EnderecoDTO("", "", "", ""));

    public static final DoadorResponseDTO DOADOR_RESPONSE_DTO_VALIDO =
            new DoadorResponseDTO("82116296072", "Gabriel", "gabrie@gmail.com",
            new Date(11, Calendar.MAY, 11, 18, 0), Genero.M, 60, "B-",
            new EnderecoDTO("Vila Maria", "SP", "SP", "02128020"));


}
