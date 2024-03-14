package com.gabriel.ferreira.souto.msdoador.application.interfaces;

import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorDTO;

public interface IDoadorService {
    DoadorDTO criarDoador(DoadorDTO doadorDTO);
    DoadorDTO atualizarDoador(DoadorDTO doadorDTO, Integer doadorId);
}
