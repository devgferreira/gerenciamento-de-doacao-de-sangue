package com.gabriel.ferreira.souto.msdoador.application.service;

import com.gabriel.ferreira.souto.msdoador.domain.interfaces.IEnderecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    private final ModelMapper _modelMapper;
    private final IEnderecoRepository _enderecoRepository;

    public EnderecoService(ModelMapper modelMapper, IEnderecoRepository enderecoRepository) {
        _modelMapper = modelMapper;
        _enderecoRepository = enderecoRepository;
    }
}


