package com.gabriel.ferreira.souto.msestoque.application.service;

import com.gabriel.ferreira.souto.msestoque.domain.repository.IEstoqueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {
    private final IEstoqueRepository _estoqueRepository;
    private final ModelMapper _modelMapper;

    public EstoqueService(IEstoqueRepository estoqueRepository, ModelMapper modelMapper) {
        _estoqueRepository = estoqueRepository;
        _modelMapper = modelMapper;
    }
}
