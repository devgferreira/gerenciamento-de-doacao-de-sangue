package com.gabriel.ferreira.souto.msdoador.domain.model.doador.request;

import com.gabriel.ferreira.souto.msdoador.domain.model.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoadorRequest {
    private String Nome;
    private String Email;
    private Date Aniversario;
    private String Genero;
    private Integer Peso;
    private String TipoSanguineo;
    private Endereco endereco;
}
