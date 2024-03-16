package com.gabriel.ferreira.souto.msdoador.application.service;

import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorRequestDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorResponseDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.EnderecoDTO;
import com.gabriel.ferreira.souto.msdoador.application.interfaces.IDoadorService;
import com.gabriel.ferreira.souto.msdoador.application.interfaces.IEnderecoService;
import com.gabriel.ferreira.souto.msdoador.domain.enums.ErrorCodes;
import com.gabriel.ferreira.souto.msdoador.domain.interfaces.IDoadorRepository;
import com.gabriel.ferreira.souto.msdoador.domain.model.doador.Doador;
import com.gabriel.ferreira.souto.msdoador.infra.exceptions.*;
import com.gabriel.ferreira.souto.msdoador.infra.exceptions.constants.ErrorConstants;
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
    public DoadorResponseDTO criarDoador(DoadorRequestDTO doadorRequestDTO) {
        Doador doador = _modelMapper.map(doadorRequestDTO, Doador.class);
        if(_doadorRepository.findByEmail(doador.getEmail()).isPresent()){
            throw new EmailJaExisteException(
                    new ExceptionResponse(ErrorCodes.EMAIL_JA_EXISTE,
                            ErrorConstants.EMAIL_JA_EXISTE));
        }
        _doadorRepository.save(doador);
        Doador result = _doadorRepository.findByEmail(doador.getEmail()).orElseThrow(
                () -> new EmailNaoEncontradoException(
                        new ExceptionResponse(ErrorCodes.EMAIL_NAO_ENCONTRADO,
                                ErrorConstants.EMAIL_NAO_ENCONTRADO))
        );
        _enderecoService.criarEndereco(doadorRequestDTO.getEndereco(), result.getId());
        return _modelMapper.map(doadorRequestDTO, DoadorResponseDTO.class);
    }

    @Override
    public DoadorResponseDTO atualizarDoador(DoadorRequestDTO doadorRequestDTO, Integer doadorId) {
        Doador doador = validarSeDoadorExisteComId(doadorId);
        EnderecoDTO enderecoDTO = _enderecoService.buscarEnderecoComDoadorId(doadorId);

        Optional.ofNullable(doadorRequestDTO.getNome()).filter(nome -> !nome.isEmpty()).ifPresent(doador::setNome);
        Optional.ofNullable(doadorRequestDTO.getEmail()).filter(email -> !email.isEmpty()).ifPresent(doador::setEmail);
        Optional.ofNullable(doadorRequestDTO.getAniversario()).ifPresent(doador::setAniversario);
        Optional.ofNullable(doadorRequestDTO.getPeso()).ifPresent(doador::setPeso);
        Optional.ofNullable(doadorRequestDTO.getGenero()).filter(genero -> !genero.isEmpty()).ifPresent(doador::setGenero);
        Optional.ofNullable(doadorRequestDTO.getTipoSanguineo()).filter(tipoSanguineo -> !tipoSanguineo.isEmpty()).ifPresent(doador::setTipoSanguineo);
        Optional.ofNullable(doadorRequestDTO.getEndereco().getBairro()).filter(bairro -> !bairro.isEmpty()).ifPresent(enderecoDTO::setBairro);
        Optional.ofNullable(doadorRequestDTO.getEndereco().getCidade()).filter(cidade -> !cidade.isEmpty()).ifPresent(enderecoDTO::setCidade);
        Optional.ofNullable(doadorRequestDTO.getEndereco().getEstado()).filter(estado -> !estado.isEmpty()).ifPresent(enderecoDTO::setEstado);
        Optional.ofNullable(doadorRequestDTO.getEndereco().getCep()).filter(cep -> !cep.isEmpty()).ifPresent(enderecoDTO::setCep);

        _doadorRepository.save(doador);
        _enderecoService.atualizarEndereco(enderecoDTO, doadorId);

        return _modelMapper.map(doadorRequestDTO, DoadorResponseDTO.class);
    }

    @Override
    public DoadorResponseDTO buscarDoadorComId(Integer doadorId) {
        Doador doador = validarSeDoadorExisteComId(doadorId);
        EnderecoDTO enderecoDTO = _enderecoService.buscarEnderecoComDoadorId(doadorId);
        DoadorResponseDTO doadorResponseDTO = _modelMapper.map(doador, DoadorResponseDTO.class);
        doadorResponseDTO.setEndereco(enderecoDTO);
        return doadorResponseDTO;
    }

    @Override
    public void deletarDoadorComId(Integer doadorId) {
       Doador doador = validarSeDoadorExisteComId(doadorId);
        _enderecoService.deletarEnderecoComDoadorId(doadorId);
        _doadorRepository.delete(doador);
    }

    private void validarDoador(DoadorRequestDTO doadorRequestDTO){
        boolean doadorInvalid = doadorRequestDTO.getNome().isEmpty() || doadorRequestDTO.getEmail().isEmpty()
                || doadorRequestDTO.getAniversario() == null || doadorRequestDTO.getPeso() == null
                || doadorRequestDTO.getGenero().isEmpty() || doadorRequestDTO.getTipoSanguineo().isEmpty();
        if (doadorInvalid){
            throw new DoadorInvalidoException(new ExceptionResponse(ErrorCodes.DOADOR_INVALIDO, ErrorConstants.DOADOR_INVALIDO));
        }
        validarEndereco(doadorRequestDTO.getEndereco());
    }

    private void validarTipoSanguineo(String tipoSanguineo){
        tipoSanguineo = tipoSanguineo.substring(0, 1).toUpperCase() + tipoSanguineo.substring(1);
        boolean tipoSanguineoValid = tipoSanguineo.equals("B+") || tipoSanguineo.equals("B-") || tipoSanguineo.equals("A+")
                || tipoSanguineo.equals("A-") || tipoSanguineo.equals("O+") || tipoSanguineo.equals("O-") ||
                tipoSanguineo.equals("AB+") || tipoSanguineo.equals("AB-");
        if(!tipoSanguineoValid){
            throw new RuntimeException("Tipo sanguineo inválido, por favor, coloque um tipo válido!");
        }

    }

    private Doador validarSeDoadorExisteComId(Integer doadorId){
        return _doadorRepository.findById(doadorId).orElseThrow(
                () -> new DoadorNaoEncontradoException(
                        new ExceptionResponse(ErrorCodes.DOADOR_NAO_ENCONTRADO,
                                ErrorConstants.DOADOR_NAO_ENCONTRADO))
        );
    }
    private void validarEndereco(EnderecoDTO endereco) {
        if (endereco.getEstado().isEmpty() || endereco.getCep().isEmpty()
                || endereco.getBairro().isEmpty() || endereco.getCidade().isEmpty()) {
            throw new EnderecoInvalidoException(
                    new ExceptionResponse(ErrorCodes.ENDERECO_INVALIDO,
                            ErrorConstants.ENDERECO_INVALIDO));
        }
    }
}
