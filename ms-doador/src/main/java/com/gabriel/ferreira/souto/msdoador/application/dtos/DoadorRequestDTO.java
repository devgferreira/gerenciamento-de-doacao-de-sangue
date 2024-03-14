package com.gabriel.ferreira.souto.msdoador.application.dtos;

import com.gabriel.ferreira.souto.msdoador.domain.model.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoadorRequestDTO {
    private String nome;
    private String email;
    private Date aniversario;
    private String genero;
    private Integer peso;
    private String tipoSanguineo;
    private EnderecoDTO endereco;
}
