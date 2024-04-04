package com.gabriel.ferreira.souto.msdoacao.domain.application.interfaces;

import com.gabriel.ferreira.souto.msdoacao.domain.application.dtos.DoacaoDTO;
import com.gabriel.ferreira.souto.msdoacao.domain.model.doacao.Doacao;

import java.util.List;

public interface IDoacaoService {
    DoacaoDTO criarDoacao(DoacaoDTO doacaoDTO);
    List<DoacaoDTO> listarTodasAsDoacao();
    DoacaoDTO buscarDoacaoPorId(Integer doacaoId);
}
