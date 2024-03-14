package com.gabriel.ferreira.souto.msdoador.application.interfaces;

import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorRequestDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorResponseDTO;

public interface IDoadorService {
    DoadorDTO criarDoador(DoadorRequestDTO doadorRequestDTO);
    DoadorDTO atualizarDoador(DoadorRequestDTO doadorRequestDTO, Integer doadorId);
    DoadorResponseDTO buscarDoadorComId(Integer doadorId);
    void deletarDoadorComId(Integer doadorId);
}
