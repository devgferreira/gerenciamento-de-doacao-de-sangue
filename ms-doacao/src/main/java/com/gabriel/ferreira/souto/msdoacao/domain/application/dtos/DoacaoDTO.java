package com.gabriel.ferreira.souto.msdoacao.domain.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoacaoDTO {
    private Integer doadorId;
    private Integer sangueML;
}
