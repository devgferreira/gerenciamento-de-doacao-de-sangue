package com.gabriel.ferreira.souto.msdoador.application.service;

import com.gabriel.ferreira.souto.msdoador.application.dtos.EnderecoDTO;
import com.gabriel.ferreira.souto.msdoador.application.interfaces.IEnderecoService;
import com.gabriel.ferreira.souto.msdoador.domain.interfaces.IEnderecoRepository;
import com.gabriel.ferreira.souto.msdoador.domain.model.endereco.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public EnderecoDTO buscarEnderecoComDoadorId(Integer doadorId) {
        Endereco endereco = _enderecoRepository.findEnderecoByDoadorId(doadorId).orElseThrow();
        return _modelMapper.map(endereco, EnderecoDTO.class);
    }

    @Override
    public EnderecoDTO atualizarEndereco(EnderecoDTO enderecoDTO, Integer doadorId) {
        Endereco endereco = _enderecoRepository.findEnderecoByDoadorId(doadorId).orElseThrow();

        if(enderecoDTO.getBairro().isEmpty()){
            endereco.setBairro(enderecoDTO.getBairro());
        }
        if(enderecoDTO.getCidade().isEmpty()){
            endereco.setCidade(enderecoDTO.getCidade());
        }
        if(enderecoDTO.getEstado().isEmpty()){
            endereco.setEstado(enderecoDTO.getEstado());
        }
        if(enderecoDTO.getCep().isEmpty()){
            endereco.setCep(enderecoDTO.getCep());
        }

        _enderecoRepository.save(endereco);

        return _modelMapper.map(endereco, EnderecoDTO.class);
    }
}
