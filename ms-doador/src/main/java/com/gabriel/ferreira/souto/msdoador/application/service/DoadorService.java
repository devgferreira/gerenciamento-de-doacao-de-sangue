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
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
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

    private static void validarDoador(DoadorRequestDTO doadorRequestDTO) {
        boolean doadorInvalido = doadorRequestDTO.getNome().isEmpty() || doadorRequestDTO.getEmail().isEmpty()
                || doadorRequestDTO.getAniversario() == null || doadorRequestDTO.getPeso() == null
                || doadorRequestDTO.getGenero() == null || doadorRequestDTO.getTipoSanguineo().isEmpty();
        if (doadorInvalido) {
            throw new DoadorInvalidoException(new ExceptionResponse(ErrorCodes.DOADOR_INVALIDO, ErrorConstants.DOADOR_INVALIDO));
        }
        if (!validarCpf(doadorRequestDTO.getCpf())) {
            throw new CpfInvalidoException(new ExceptionResponse(ErrorCodes.CPF_INVALIDO, ErrorConstants.CPF_INVALIDO));
        }
        validarEndereco(doadorRequestDTO.getEndereco());
    }

    private static void validarEndereco(EnderecoDTO endereco) {
        if (endereco.getEstado().isEmpty() || endereco.getCep().isEmpty()
                || endereco.getBairro().isEmpty() || endereco.getCidade().isEmpty()) {
            throw new EnderecoInvalidoException(
                    new ExceptionResponse(ErrorCodes.ENDERECO_INVALIDO,
                            ErrorConstants.ENDERECO_INVALIDO));
        }
    }

    private static void validarTipoSanguineo(String tipoSanguineo) {
        tipoSanguineo = tipoSanguineo.substring(0, 1).toUpperCase() + tipoSanguineo.substring(1);
        boolean tipoSanguineoValid = tipoSanguineo.equals("B+") || tipoSanguineo.equals("B-") || tipoSanguineo.equals("A+")
                || tipoSanguineo.equals("A-") || tipoSanguineo.equals("O+") || tipoSanguineo.equals("O-") ||
                tipoSanguineo.equals("AB+") || tipoSanguineo.equals("AB-");
        if (!tipoSanguineoValid) {
            throw new TipoSanguineoInvalidoException(new ExceptionResponse(ErrorCodes.TIPO_SANGUINEO_INVALIDO,
                    ErrorConstants.TIPO_SANGUINEO_INVALIDO));
        }
    }

    private static boolean validarCpf(String cpf) {
        CPFValidator validator = new CPFValidator();
        validator.initialize(null);
        return validator.isValid(cpf, null);
    }

    @Override
    public DoadorResponseDTO criarDoador(DoadorRequestDTO doadorRequestDTO) {
        validarDoador(doadorRequestDTO);
        validarTipoSanguineo(doadorRequestDTO.getTipoSanguineo());
        if (_doadorRepository.findByEmail(doadorRequestDTO.getEmail()).isPresent()) {
            throw new EmailJaExisteException(
                    new ExceptionResponse(ErrorCodes.EMAIL_JA_EXISTE,
                            ErrorConstants.EMAIL_JA_EXISTE));
        }
        if (_doadorRepository.findByCpf(doadorRequestDTO.getCpf()).isPresent()) {
            throw new CpfJaExisteException(
                    new ExceptionResponse(ErrorCodes.CPF_JA_EXISTE,
                            ErrorConstants.CPF_JA_EXISTE));
        }
        Doador doador = _modelMapper.map(doadorRequestDTO, Doador.class);
        _doadorRepository.save(doador);
        _enderecoService.criarEndereco(doadorRequestDTO.getEndereco(), doadorRequestDTO.getCpf());
        return _modelMapper.map(doadorRequestDTO, DoadorResponseDTO.class);
    }

    @Override
    public DoadorResponseDTO atualizarDoador(DoadorRequestDTO doadorRequestDTO, Integer doadorId) {
        Doador doador = validarSeDoadorExisteComId(doadorId);
        EnderecoDTO enderecoDTO = _enderecoService.buscarEnderecoComDoadorCpf(doador.getCpf());

        Optional.ofNullable(doadorRequestDTO.getNome()).filter(nome -> !nome.isEmpty()).ifPresent(doador::setNome);
        Optional.ofNullable(doadorRequestDTO.getEmail()).filter(email -> !email.isEmpty()).ifPresent(doador::setEmail);
        Optional.ofNullable(doadorRequestDTO.getAniversario()).ifPresent(doador::setAniversario);
        Optional.ofNullable(doadorRequestDTO.getPeso()).ifPresent(doador::setPeso);
        Optional.ofNullable(doadorRequestDTO.getGenero()).ifPresent(doador::setGenero);
        Optional.ofNullable(doadorRequestDTO.getTipoSanguineo()).filter(tipoSanguineo -> !tipoSanguineo.isEmpty()).ifPresent(doador::setTipoSanguineo);
        Optional.ofNullable(doadorRequestDTO.getEndereco().getBairro()).filter(bairro -> !bairro.isEmpty()).ifPresent(enderecoDTO::setBairro);
        Optional.ofNullable(doadorRequestDTO.getEndereco().getCidade()).filter(cidade -> !cidade.isEmpty()).ifPresent(enderecoDTO::setCidade);
        Optional.ofNullable(doadorRequestDTO.getEndereco().getEstado()).filter(estado -> !estado.isEmpty()).ifPresent(enderecoDTO::setEstado);
        Optional.ofNullable(doadorRequestDTO.getEndereco().getCep()).filter(cep -> !cep.isEmpty()).ifPresent(enderecoDTO::setCep);

        _doadorRepository.save(doador);
        _enderecoService.atualizarEndereco(enderecoDTO, doador.getCpf());

        return _modelMapper.map(doadorRequestDTO, DoadorResponseDTO.class);
    }

    @Override
    public DoadorResponseDTO buscarDoadorComId(Integer doadorId) {
        Doador doador = validarSeDoadorExisteComId(doadorId);
        EnderecoDTO enderecoDTO = _enderecoService.buscarEnderecoComDoadorCpf(doador.getCpf());
        DoadorResponseDTO doadorResponseDTO = _modelMapper.map(doador, DoadorResponseDTO.class);
        doadorResponseDTO.setEndereco(enderecoDTO);
        return doadorResponseDTO;
    }

    @Override
    public DoadorResponseDTO buscarDoadorPorCpf(String cpf) {
        Doador doador = _doadorRepository.findByCpf(cpf).orElseThrow(
                () -> new DoadorNaoEncontradoException(new ExceptionResponse(ErrorCodes.DOADOR_NAO_ENCONTRADO,
                        ErrorConstants.DOADOR_NAO_ENCONTRADO))
        );
        EnderecoDTO enderecoDTO = _enderecoService.buscarEnderecoComDoadorCpf(doador.getCpf());
        DoadorResponseDTO doadorResponseDTO = _modelMapper.map(doador, DoadorResponseDTO.class);
        doadorResponseDTO.setEndereco(enderecoDTO);
        return doadorResponseDTO;
    }

    @Override
    public void deletarDoadorComId(Integer doadorId) {
        Doador doador = validarSeDoadorExisteComId(doadorId);
        _enderecoService.deletarEnderecoComDoadorCpf(doador.getCpf());
        _doadorRepository.delete(doador);
    }

    private Doador validarSeDoadorExisteComId(Integer doadorId) {
        return _doadorRepository.findById(doadorId).orElseThrow(
                () -> new DoadorNaoEncontradoException(
                        new ExceptionResponse(ErrorCodes.DOADOR_NAO_ENCONTRADO,
                                ErrorConstants.DOADOR_NAO_ENCONTRADO))
        );
    }
}
