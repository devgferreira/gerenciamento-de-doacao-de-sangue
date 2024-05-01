package com.gabriel.ferreira.souto.msdoador.application.dtos;

import com.gabriel.ferreira.souto.msdoador.domain.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoadorResponseDTO {
    private String cpf;
    private String nome;
    private String email;
    private Date aniversario;
    private Genero genero;
    private Integer peso;
    private String tipoSanguineo;
    private EnderecoDTO endereco;
}
