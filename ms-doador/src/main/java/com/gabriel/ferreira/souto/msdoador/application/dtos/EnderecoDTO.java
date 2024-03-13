package com.gabriel.ferreira.souto.msdoador.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    private Integer Id;
    private String Bairro;
    private String Cidade;
    private String Estado;
    private String Cep;
    private Integer DoadorId;
}
