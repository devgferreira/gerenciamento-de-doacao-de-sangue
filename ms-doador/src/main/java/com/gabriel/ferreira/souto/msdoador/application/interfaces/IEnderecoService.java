package com.gabriel.ferreira.souto.msdoador.application.interfaces;

import com.gabriel.ferreira.souto.msdoador.application.dtos.EnderecoDTO;
import com.gabriel.ferreira.souto.msdoador.domain.model.endereco.Endereco;

public interface IEnderecoService {
    EnderecoDTO criarEndereco(EnderecoDTO enderecoDTO);
    EnderecoDTO buscarEnderecoComDoadorId(Integer doadorId);
    EnderecoDTO atualizarEndereco(EnderecoDTO enderecoDTO, Integer doadorId);
}
