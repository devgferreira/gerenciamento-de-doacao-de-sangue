package com.gabriel.ferreira.souto.msestoque.application.interfaces;

import com.gabriel.ferreira.souto.msestoque.application.dto.EstoqueDTO;

import java.util.List;

public interface IEstoqueService {
    List<EstoqueDTO> bsucarTodasOsEstoques();
    EstoqueDTO buscarEstoquePorId(Integer id);
}
