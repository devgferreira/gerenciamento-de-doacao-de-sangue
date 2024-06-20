package com.gabriel.ferreira.souto.msdoador.application.interfaces;

import com.gabriel.ferreira.souto.msdoador.application.dtos.EnderecoDTO;

public interface IEnderecoService {
    EnderecoDTO criarEndereco(EnderecoDTO enderecoDTO, String doadorCpf);

    EnderecoDTO buscarEnderecoComDoadorId(Integer doadorId);

    EnderecoDTO atualizarEndereco(EnderecoDTO enderecoDTO, Integer doadorId);

    void deletarEnderecoComDoadorId(Integer doadorId);
}
