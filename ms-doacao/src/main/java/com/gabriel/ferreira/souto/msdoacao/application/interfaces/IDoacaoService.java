package com.gabriel.ferreira.souto.msdoacao.application.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gabriel.ferreira.souto.msdoacao.application.dtos.DoacaoDTO;

import java.util.List;

public interface IDoacaoService {
    DoacaoDTO criarDoacao(DoacaoDTO doacaoDTO) throws JsonProcessingException;
    List<DoacaoDTO> listarTodasAsDoacao();
    DoacaoDTO buscarDoacaoPorId(Integer doacaoId);
}
