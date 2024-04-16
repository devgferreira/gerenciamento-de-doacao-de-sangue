package com.gabriel.ferreira.souto.msestoque.application.service;

import com.gabriel.ferreira.souto.msestoque.application.dto.EstoqueDTO;
import com.gabriel.ferreira.souto.msestoque.application.interfaces.IEstoqueService;
import com.gabriel.ferreira.souto.msestoque.domain.repository.IEstoqueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService implements IEstoqueService {
    private final IEstoqueRepository _estoqueRepository;
    private final ModelMapper _modelMapper;

    public EstoqueService(IEstoqueRepository estoqueRepository, ModelMapper modelMapper) {
        _estoqueRepository = estoqueRepository;
        _modelMapper = modelMapper;
    }

    @Override
    public List<EstoqueDTO> bsucarTodasOsEstoques() {
        return null;
    }

    @Override
    public EstoqueDTO buscarEstoquePorId(Integer id) {
        return null;
    }
}
