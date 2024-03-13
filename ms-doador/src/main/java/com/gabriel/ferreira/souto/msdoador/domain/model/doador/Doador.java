package com.gabriel.ferreira.souto.msdoador.domain.model.doador;

import com.gabriel.ferreira.souto.msdoador.domain.model.endereco.Endereco;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String Nome;
    private String Email;
    private Date Aniversario;
    private String Genero;
    private Integer Peso;
    private String TipoSanguineo;
}
