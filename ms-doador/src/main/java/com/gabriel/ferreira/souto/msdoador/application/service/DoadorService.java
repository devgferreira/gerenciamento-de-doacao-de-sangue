package com.gabriel.ferreira.souto.msdoador.application.service;

import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorRequestDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorResponseDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.EnderecoDTO;
import com.gabriel.ferreira.souto.msdoador.application.interfaces.IDoadorService;
import com.gabriel.ferreira.souto.msdoador.application.interfaces.IEnderecoService;
import com.gabriel.ferreira.souto.msdoador.domain.interfaces.IDoadorRepository;
import com.gabriel.ferreira.souto.msdoador.domain.model.doador.Doador;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoadorService implements IDoadorService {

    private final ModelMapper _modelMapper;
    private final IEnderecoService _enderecoService;
    private final IDoadorRepository _doadorRepository;

    @Autowired
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
    public DoadorDTO atualizarDoador(DoadorRequestDTO doadorRequestDTO, Integer doadorId) {
        Doador doador = _doadorRepository.findById(doadorId).orElseThrow();
        EnderecoDTO enderecoDTO = _enderecoService.buscarEnderecoComDoadorId(doadorId);

        Optional.ofNullable(doadorRequestDTO.getNome()).filter(nome -> !nome.isEmpty()).ifPresent(doador::setNome);
        Optional.ofNullable(doadorRequestDTO.getEmail()).filter(email -> !email.isEmpty()).ifPresent(doador::setEmail);
        Optional.ofNullable(doadorRequestDTO.getAniversario()).ifPresent(doador::setAniversario);
        Optional.ofNullable(doadorRequestDTO.getPeso()).ifPresent(doador::setPeso);
        Optional.ofNullable(doadorRequestDTO.getGenero()).filter(genero -> !genero.isEmpty()).ifPresent(doador::setGenero);
        Optional.ofNullable(doadorRequestDTO.getTipoSanguineo()).filter(tipoSanguineo -> !tipoSanguineo.isEmpty()).ifPresent(doador::setTipoSanguineo);
        Optional.ofNullable(doadorRequestDTO.getEnderecoDTO().getBairro()).filter(bairro -> !bairro.isEmpty()).ifPresent(enderecoDTO::setBairro);
        Optional.ofNullable(doadorRequestDTO.getEnderecoDTO().getCidade()).filter(cidade -> !cidade.isEmpty()).ifPresent(enderecoDTO::setCidade);
        Optional.ofNullable(doadorRequestDTO.getEnderecoDTO().getEstado()).filter(estado -> !estado.isEmpty()).ifPresent(enderecoDTO::setEstado);
        Optional.ofNullable(doadorRequestDTO.getEnderecoDTO().getCep()).filter(cep -> !cep.isEmpty()).ifPresent(enderecoDTO::setCep);

        _doadorRepository.save(doador);
        _enderecoService.atualizarEndereco(enderecoDTO, doadorId);

        return _modelMapper.map(doador, DoadorDTO.class);
    }

    @Override
    public DoadorResponseDTO buscarDoadorComId(Integer doadorId) {
        Doador doador = _doadorRepository.findById(doadorId).orElseThrow();
        EnderecoDTO enderecoDTO = _enderecoService.buscarEnderecoComDoadorId(doadorId);
        DoadorResponseDTO doadorResponseDTO = _modelMapper.map(doador, DoadorResponseDTO.class);
        doadorResponseDTO.setEnderecoDTO(enderecoDTO);
        return doadorResponseDTO;
    }

    @Override
    public void deletarDoadorComId(Integer doadorId) {
        Doador doador =_doadorRepository.findById(doadorId).orElseThrow();
        _doadorRepository.delete(doador);
    }
}
