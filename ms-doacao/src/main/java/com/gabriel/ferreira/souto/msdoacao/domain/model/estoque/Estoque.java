package com.gabriel.ferreira.souto.msdoacao.domain.model.estoque;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Estoque {
    private String tipoSanguineo;
    private Integer sangueML;
}
