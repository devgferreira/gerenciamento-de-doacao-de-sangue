package com.gabriel.ferreira.souto.msdoador.application.interfaces;

import com.gabriel.ferreira.souto.msdoador.application.dtos.EnderecoDTO;

public interface IEnderecoService {
    EnderecoDTO criarEndereco(EnderecoDTO enderecoDTO, String doadorCpf);

    EnderecoDTO buscarEnderecoComDoadorCpf(String doadorCpf);

    EnderecoDTO atualizarEndereco(EnderecoDTO enderecoDTO, String doadorCpf);

    void deletarEnderecoComDoadorCpf(String doadorCpf);
}
