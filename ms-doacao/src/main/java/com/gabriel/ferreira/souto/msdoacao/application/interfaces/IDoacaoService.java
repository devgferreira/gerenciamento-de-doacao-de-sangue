package com.gabriel.ferreira.souto.msdoacao.application.interfaces;

import com.gabriel.ferreira.souto.msdoacao.application.dtos.DoacaoDTO;

import java.util.List;

public interface IDoacaoService {
    DoacaoDTO criarDoacao(DoacaoDTO doacaoDTO);
    List<DoacaoDTO> listarTodasAsDoacao();
    DoacaoDTO buscarDoacaoPorId(Integer doacaoId);
}
