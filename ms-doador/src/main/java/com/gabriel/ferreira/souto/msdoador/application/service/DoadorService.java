package com.gabriel.ferreira.souto.msdoador.application.service;

import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorRequestDTO;
import com.gabriel.ferreira.souto.msdoador.application.interfaces.IDoadorService;
import com.gabriel.ferreira.souto.msdoador.application.interfaces.IEnderecoService;
import com.gabriel.ferreira.souto.msdoador.domain.interfaces.IDoadorRepository;
import com.gabriel.ferreira.souto.msdoador.domain.model.doador.Doador;
import com.gabriel.ferreira.souto.msdoador.domain.model.doador.request.DoadorRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DoadorService implements IDoadorService {

    private final ModelMapper _modelMapper;
    private final IEnderecoService _enderecoService;
    private final IDoadorRepository _doadorRepository;

    public DoadorService(ModelMapper modelMapper, IEnderecoService enderecoService, IDoadorRepository doadorRepository) {
        _modelMapper = modelMapper;
        _enderecoService = enderecoService;
        _doadorRepository = doadorRepository;
    }

    @Override
    public DoadorDTO criarDoador(DoadorRequestDTO doadorRequestDTO) {
        Doador doador = _modelMapper.map(doadorRequestDTO, Doador.class);
        _enderecoService.criarEndereco(doadorRequestDTO.getEnderecoDTO());
        return _modelMapper.map(_doadorRepository.save(doador), DoadorDTO.class);
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
