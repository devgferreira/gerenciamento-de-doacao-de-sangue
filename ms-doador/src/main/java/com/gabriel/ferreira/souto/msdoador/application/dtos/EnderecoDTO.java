package com.gabriel.ferreira.souto.msdoador.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    private Integer id;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private Integer doadorId;
}
