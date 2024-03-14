package com.gabriel.ferreira.souto.msdoador.application.service;

import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorDTO;
import com.gabriel.ferreira.souto.msdoador.application.interfaces.IDoadorService;
import com.gabriel.ferreira.souto.msdoador.domain.interfaces.IDoadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DoadorService implements IDoadorService {

    private final ModelMapper _modelMapper;
    private final IDoadorRepository _doadorRepository;

    public DoadorService(ModelMapper modelMapper, IDoadorRepository doadorRepository) {
        _modelMapper = modelMapper;
        _doadorRepository = doadorRepository;
    }

    @Override
    public DoadorDTO criarDoador(DoadorDTO doadorDTO) {
        return null;
    }

    @Override
    public DoadorDTO atualizarDoador(DoadorDTO doadorDTO, Integer doadorId) {
        return null;
    }

    @Override
    public DoadorDTO buscarDoadorComId(Integer doadorId) {
        return null;
    }

    @Override
    public void deletarDoadorComId(Integer doadorId) {

    }
}
