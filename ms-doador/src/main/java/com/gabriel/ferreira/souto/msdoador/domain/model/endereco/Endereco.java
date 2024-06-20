package com.gabriel.ferreira.souto.msdoador.domain.model.endereco;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    @JoinColumn(name = "doador_cpf")
    private String doadorCpf;
}
