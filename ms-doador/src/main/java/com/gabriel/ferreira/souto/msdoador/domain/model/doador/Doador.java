package com.gabriel.ferreira.souto.msdoador.domain.model.doador;

import com.gabriel.ferreira.souto.msdoador.domain.enums.Genero;
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
    private Integer id;
    private String cpf;
    private String nome;
    private String email;
    private Date aniversario;
    @Enumerated(value = EnumType.STRING)
    private Genero genero;
    private Integer peso;
    private String tipoSanguineo;
}
