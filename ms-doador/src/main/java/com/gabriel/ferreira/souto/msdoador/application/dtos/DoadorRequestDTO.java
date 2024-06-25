package com.gabriel.ferreira.souto.msdoador.application.dtos;

import com.gabriel.ferreira.souto.msdoador.domain.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoadorRequestDTO {
    private String cpf;
    private String nome;
    private String email;
    private Date aniversario;
    private Genero genero;
    private Integer peso;
    private String tipoSanguineo;
    private EnderecoDTO endereco;

    public DoadorRequestDTO(String cpf, String nome, String email, Date aniversario, Genero genero, Integer peso, String tipoSanguineo) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.aniversario = aniversario;
        this.genero = genero;
        this.peso = peso;
        this.tipoSanguineo = tipoSanguineo;
    }
}
