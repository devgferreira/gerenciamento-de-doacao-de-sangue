package com.gabriel.ferreira.souto.msdoador.domain.model.endereco;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String Bairro;
    private String Cidade;
    private String Estado;
    private String Cep;
}
