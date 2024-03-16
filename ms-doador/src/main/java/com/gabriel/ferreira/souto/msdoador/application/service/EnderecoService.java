package com.gabriel.ferreira.souto.msdoador.application.service;

import com.gabriel.ferreira.souto.msdoador.application.dtos.EnderecoDTO;
import com.gabriel.ferreira.souto.msdoador.application.interfaces.IEnderecoService;
import com.gabriel.ferreira.souto.msdoador.domain.enums.ErrorCodes;
import com.gabriel.ferreira.souto.msdoador.domain.interfaces.IEnderecoRepository;
import com.gabriel.ferreira.souto.msdoador.domain.model.endereco.Endereco;
import com.gabriel.ferreira.souto.msdoador.infra.exceptions.EnderecoNaoEncontradoException;
import com.gabriel.ferreira.souto.msdoador.infra.exceptions.ExceptionResponse;
import com.gabriel.ferreira.souto.msdoador.infra.exceptions.constants.ErrorConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService implements IEnderecoService {
    private final ModelMapper _modelMapper;
    private final IEnderecoRepository _enderecoRepository;

    @Autowired
    public EnderecoService(ModelMapper modelMapper, IEnderecoRepository enderecoRepository) {
        _modelMapper = modelMapper;
        _enderecoRepository = enderecoRepository;
    }

    @Override
    public EnderecoDTO criarEndereco(EnderecoDTO enderecoDTO, Integer doadorId) {
        Endereco endereco = _modelMapper.map(enderecoDTO, Endereco.class);
        endereco.setDoadorId(doadorId);
        return _modelMapper.map(_enderecoRepository.save(endereco), EnderecoDTO.class);
    }

    @Override
    public EnderecoDTO buscarEnderecoComDoadorId(Integer doadorId) {
        Endereco endereco = _enderecoRepository.findByDoadorId(doadorId).orElseThrow(
                () -> new EnderecoNaoEncontradoException(
                        new ExceptionResponse(ErrorCodes.ENDERECO_NAO_ENCONTRADO,
                                ErrorConstants.EMAIL_NAO_ENCONTRADO))
        );
        return _modelMapper.map(endereco, EnderecoDTO.class);
    }

    @Override
    public EnderecoDTO atualizarEndereco(EnderecoDTO enderecoDTO, Integer doadorId) {
        Endereco endereco = _enderecoRepository.findByDoadorId(doadorId).orElseThrow();

        Optional.ofNullable(enderecoDTO.getBairro()).filter(bairro -> !bairro.isEmpty()).ifPresent(endereco::setBairro);
        Optional.ofNullable(enderecoDTO.getCidade()).filter(cidade -> !cidade.isEmpty()).ifPresent(endereco::setCidade);
        Optional.ofNullable(enderecoDTO.getEstado()).filter(estado -> !estado.isEmpty()).ifPresent(endereco::setEstado);
        Optional.ofNullable(enderecoDTO.getCep()).filter(cep -> !cep.isEmpty()).ifPresent(endereco::setCep);

        _enderecoRepository.save(endereco);

        return _modelMapper.map(endereco, EnderecoDTO.class);
    }

    @Override
    public void deletarEnderecoComDoadorId(Integer doadorId) {
        Endereco endereco = _enderecoRepository.findByDoadorId(doadorId).orElseThrow();
        _enderecoRepository.delete(endereco);
    }
}
