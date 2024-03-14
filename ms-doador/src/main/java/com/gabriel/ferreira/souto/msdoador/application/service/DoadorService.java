package com.gabriel.ferreira.souto.msdoador.application.service;

import com.gabriel.ferreira.souto.msdoador.domain.interfaces.IDoadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DoadorService {

    private final ModelMapper _modelMapper;
    private final IDoadorRepository _doadorRepository;

    public DoadorService(ModelMapper modelMapper, IDoadorRepository doadorRepository) {
        _modelMapper = modelMapper;
        _doadorRepository = doadorRepository;
    }
}
