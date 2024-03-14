package com.gabriel.ferreira.souto.msdoador.application.interfaces;

import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorRequestDTO;
import com.gabriel.ferreira.souto.msdoador.domain.model.doador.request.DoadorRequest;

public interface IDoadorService {
    DoadorDTO criarDoador(DoadorRequestDTO doadorRequestDTO);
    DoadorDTO atualizarDoador(DoadorDTO doadorDTO, Integer doadorId);
    DoadorDTO buscarDoadorComId(Integer doadorId);
    void deletarDoadorComId(Integer doadorId);
}
