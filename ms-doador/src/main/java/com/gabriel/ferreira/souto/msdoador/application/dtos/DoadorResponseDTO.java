package com.gabriel.ferreira.souto.msdoador.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoadorResponseDTO {
    private String nome;
    private String email;
    private Date aniversario;
    private String genero;
    private Integer peso;
    private String tipoSanguineo;
    private EnderecoDTO endereco;

    public DoadorResponseDTO(DoadorDTO doadorDTO) {
        nome = doadorDTO.getNome();
        email = doadorDTO.getEmail();
        aniversario = doadorDTO.getAniversario();
        genero = doadorDTO.getGenero();
        peso = doadorDTO.getPeso();
        tipoSanguineo = doadorDTO.getTipoSanguineo();;
    }
}
