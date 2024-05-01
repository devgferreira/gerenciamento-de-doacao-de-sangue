package com.gabriel.ferreira.souto.msestoque.application.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueDTO {
    private Integer id;
    private String tipoSanguineo;
    private Integer sangueML;
}
