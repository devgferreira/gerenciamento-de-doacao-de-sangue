package com.gabriel.ferreira.souto.msdoador.common;

import com.gabriel.ferreira.souto.msdoador.domain.enums.Genero;
import com.gabriel.ferreira.souto.msdoador.domain.model.doador.Doador;

import java.util.Date;

public class DoadorConstants {
    private static final Doador DOADOR_VALIDO = new Doador("82116296072", "Gabriel", "gabrie@gmail.com",
            new Date(), Genero.M, 60, "B-");
}
