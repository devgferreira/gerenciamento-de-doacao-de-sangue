package com.gabriel.ferreira.souto.msdoacao.domain.model.doador;

import lombok.Data;

import java.util.Date;

@Data
public class DoadorResponse {
    private String nome;
    private String email;
    private Date aniversario;
    private String genero;
    private Integer peso;
    private String tipoSanguineo;
}
