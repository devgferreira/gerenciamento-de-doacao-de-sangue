package com.gabriel.ferreira.souto.msdoador.application.interfaces;

import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorRequestDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorResponseDTO;

public interface IDoadorService {
    DoadorResponseDTO criarDoador(DoadorRequestDTO doadorRequestDTO);
    DoadorResponseDTO atualizarDoador(DoadorRequestDTO doadorRequestDTO, Integer doadorId);
    DoadorResponseDTO buscarDoadorComId(Integer doadorId);
    void deletarDoadorComId(Integer doadorId);
}
