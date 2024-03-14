package com.gabriel.ferreira.souto.msdoador.application.service;

import com.gabriel.ferreira.souto.msdoador.application.dtos.EnderecoDTO;
import com.gabriel.ferreira.souto.msdoador.application.interfaces.IEnderecoService;
import com.gabriel.ferreira.souto.msdoador.domain.interfaces.IEnderecoRepository;
import com.gabriel.ferreira.souto.msdoador.domain.model.endereco.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService implements IEnderecoService {
    private final ModelMapper _modelMapper;
    private final IEnderecoRepository _enderecoRepository;

    public EnderecoService(ModelMapper modelMapper, IEnderecoRepository enderecoRepository) {
        _modelMapper = modelMapper;
        _enderecoRepository = enderecoRepository;
    }

    @Override
    public EnderecoDTO criarEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = _modelMapper.map(enderecoDTO, Endereco.class);
        return _modelMapper.map(_enderecoRepository.save(endereco), EnderecoDTO.class);
    }
}
