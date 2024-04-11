package com.gabriel.ferreira.souto.msdoacao.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoacaoDTO {
    private String cpf;
    private Integer doadorId;
    private Integer sangueML;
}
