package com.gabriel.ferreira.souto.msdoador.application.service;

import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorResponseDTO;
import com.gabriel.ferreira.souto.msdoador.application.interfaces.IEnderecoService;
import com.gabriel.ferreira.souto.msdoador.domain.interfaces.IDoadorRepository;
import com.gabriel.ferreira.souto.msdoador.domain.model.doador.Doador;
import com.gabriel.ferreira.souto.msdoador.infra.exceptions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static com.gabriel.ferreira.souto.msdoador.common.DoadorConstants.*;
import static com.gabriel.ferreira.souto.msdoador.common.EnderecoConstants.ENDERECO_DTO_VALIDO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoadorServiceTest {
    @Mock
    private IDoadorRepository _doadorRepository;
    @Mock
    private IEnderecoService _enderecoService;
    @Mock
    private ModelMapper _modelMapper;
    @InjectMocks
    private DoadorService _doadorService;

    @Test
    void testCriarDoador_ComDoadorValidoSalvo_RetornandoDoador() {

        when(_doadorRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(_doadorRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        when(_modelMapper.map(DOADOR_REQUEST_DTO_VALIDO, Doador.class)).thenReturn(DOADOR_VALIDO);
        when(_doadorRepository.save(DOADOR_VALIDO)).thenReturn(DOADOR_VALIDO);
        when(_enderecoService.criarEndereco(DOADOR_REQUEST_DTO_VALIDO.getEndereco(),
                DOADOR_REQUEST_DTO_VALIDO.getCpf())).thenReturn(ENDERECO_DTO_VALIDO);
        when(_modelMapper.map(DOADOR_REQUEST_DTO_VALIDO, DoadorResponseDTO.class)).thenReturn(DOADOR_RESPONSE_DTO_VALIDO);


        DoadorResponseDTO result = _doadorService.criarDoador(DOADOR_REQUEST_DTO_VALIDO);

        assertNotNull(result);
        assertEquals(DOADOR_REQUEST_DTO_VALIDO.getCpf(), result.getCpf());
    }

    @Test
    void testCriarDoador_QuandoEmailJaExiste_RetornandoThrowEmailJaExisteException() {
        when(_doadorRepository.findByEmail(anyString())).thenReturn(Optional.of(DOADOR_VALIDO));

        assertThrows(EmailJaExisteException.class, () -> {
            _doadorService.criarDoador(DOADOR_REQUEST_DTO_VALIDO);
        });
    }

    @Test
    void testCriarDoador_QuandoCpfJaExiste_RetornandoThrowCpfJaExisteException() {
        when(_doadorRepository.findByCpf(anyString())).thenReturn(Optional.of(DOADOR_VALIDO));

        assertThrows(CpfJaExisteException.class, () -> {
            _doadorService.criarDoador(DOADOR_REQUEST_DTO_VALIDO);
        });
    }

    @Test
    void testCriarDoador_ComDoadorInvalido_RetornandoThrowDoadorInvalidoException() {

        assertThrows(DoadorInvalidoException.class, () -> {
            _doadorService.criarDoador(DOADOR_REQUEST_DTO_INVALIDO);
        });
    }

    @Test
    void testCriarDoador_QuandoCpfInvalido_RetornandoThrowCpfInvalidoException() {

        assertThrows(CpfInvalidoException.class, () -> {
            _doadorService.criarDoador(DOADOR_REQUEST_DTO_COM_CPF_INVALIDO);
        });
    }

    @Test
    void testCriarDoador_QuandoTipoSanguineioInvalido_RetornandoThrowTipoSanguineoInvalidoException() {

        assertThrows(TipoSanguineoInvalidoException.class, () -> {
            _doadorService.criarDoador(DOADOR_REQUEST_DTO_COM_TIPO_SANGUINEO_INVALIDO);
        });
    }

    @Test
    void testCriarDoador_QuandoEnderecoInvalido_RetornandoThrowEnderecoInvalidoException() {

        assertThrows(EnderecoInvalidoException.class, () -> {
            _doadorService.criarDoador(DOADOR_REQUEST_DTO_COM_ENDERECO_INVALIDO);
        });
    }

    @Test
    void testAtualizarDoador_ComDoadorValido_RetornandoDoadorValido() {

        Optional.ofNullable(DOADOR_REQUEST_DTO_VALIDO.getNome()).filter(nome -> !nome.isEmpty()).ifPresent(DOADOR_VALIDO_2::setNome);
        Optional.ofNullable(DOADOR_REQUEST_DTO_VALIDO.getEmail()).filter(email -> !email.isEmpty()).ifPresent(DOADOR_VALIDO_2::setEmail);
        Optional.ofNullable(DOADOR_REQUEST_DTO_VALIDO.getAniversario()).ifPresent(DOADOR_VALIDO_2::setAniversario);
        Optional.ofNullable(DOADOR_REQUEST_DTO_VALIDO.getPeso()).ifPresent(DOADOR_VALIDO_2::setPeso);
        Optional.ofNullable(DOADOR_REQUEST_DTO_VALIDO.getGenero()).ifPresent(DOADOR_VALIDO_2::setGenero);
        Optional.ofNullable(DOADOR_REQUEST_DTO_VALIDO.getTipoSanguineo()).filter(tipoSanguineo -> !tipoSanguineo.isEmpty()).ifPresent(DOADOR_VALIDO_2::setTipoSanguineo);
        Optional.ofNullable(DOADOR_REQUEST_DTO_VALIDO.getEndereco().getBairro()).filter(bairro -> !bairro.isEmpty()).ifPresent(ENDERECO_DTO_VALIDO::setBairro);
        Optional.ofNullable(DOADOR_REQUEST_DTO_VALIDO.getEndereco().getCidade()).filter(cidade -> !cidade.isEmpty()).ifPresent(ENDERECO_DTO_VALIDO::setCidade);
        Optional.ofNullable(DOADOR_REQUEST_DTO_VALIDO.getEndereco().getEstado()).filter(estado -> !estado.isEmpty()).ifPresent(ENDERECO_DTO_VALIDO::setEstado);
        Optional.ofNullable(DOADOR_REQUEST_DTO_VALIDO.getEndereco().getCep()).filter(cep -> !cep.isEmpty()).ifPresent(ENDERECO_DTO_VALIDO::setCep);


        when(_doadorRepository.findById(anyInt())).thenReturn(Optional.of(DOADOR_VALIDO));
        when(_enderecoService.buscarEnderecoComDoadorCpf(DOADOR_VALIDO.getCpf())).thenReturn(ENDERECO_DTO_VALIDO);


        when(_doadorRepository.save(DOADOR_VALIDO)).thenReturn(DOADOR_VALIDO);
        when(_enderecoService.atualizarEndereco(DOADOR_REQUEST_DTO_VALIDO.getEndereco(),
                DOADOR_VALIDO.getCpf())).thenReturn(ENDERECO_DTO_VALIDO);
        when(_modelMapper.map(DOADOR_REQUEST_DTO_VALIDO, DoadorResponseDTO.class)).thenReturn(DOADOR_RESPONSE_DTO_VALIDO);

        DoadorResponseDTO result = _doadorService.atualizarDoador(DOADOR_REQUEST_DTO_VALIDO, anyInt());

        assertNotNull(result);
    }

    @Test
    void testAtualizarDoador_QuandoDoadorNaoEncontrado_RetornandoThrowDoadorNaoEncontradoException() {

        assertThrows(DoadorNaoEncontradoException.class, () -> {
            _doadorService.atualizarDoador(DOADOR_REQUEST_DTO_COM_ENDERECO_INVALIDO, anyInt());
        });
    }

    @Test
    void testBuscarDoadorComId_ComDoadorEncontrado_RetornadoDoador() {
        DOADOR_RESPONSE_DTO_VALIDO.setEndereco(ENDERECO_DTO_VALIDO);

        when(_doadorRepository.findById(1)).thenReturn(Optional.of(DOADOR_VALIDO));
        when(_enderecoService.buscarEnderecoComDoadorCpf(DOADOR_VALIDO.getCpf())).thenReturn(ENDERECO_DTO_VALIDO);
        when(_modelMapper.map(DOADOR_VALIDO, DoadorResponseDTO.class)).thenReturn(DOADOR_RESPONSE_DTO_VALIDO);

        DoadorResponseDTO result = _doadorService.buscarDoadorComId(1);

        assertNotNull(result);
        assertEquals(DOADOR_RESPONSE_DTO_VALIDO, result);
    }

    @Test
    void testBuscarDoadorComId_ComDoadorNaoEncontrado_RetornadoThrowDoadorNaoEncontradoException() {
        assertThrows(DoadorNaoEncontradoException.class, () -> {
            _doadorService.buscarDoadorComId(anyInt());
        });
    }

    @Test
    void testBuscarDoadorPorCpf_ComDoadorEncontrado_RetornadoDoador() {
        DOADOR_RESPONSE_DTO_VALIDO.setEndereco(ENDERECO_DTO_VALIDO);

        when(_doadorRepository.findByCpf(anyString())).thenReturn(Optional.of(DOADOR_VALIDO));
        when(_enderecoService.buscarEnderecoComDoadorCpf(DOADOR_VALIDO.getCpf())).thenReturn(ENDERECO_DTO_VALIDO);
        when(_modelMapper.map(DOADOR_VALIDO, DoadorResponseDTO.class)).thenReturn(DOADOR_RESPONSE_DTO_VALIDO);

        DoadorResponseDTO result = _doadorService.buscarDoadorPorCpf(anyString());

        assertNotNull(result);
        assertEquals(DOADOR_RESPONSE_DTO_VALIDO, result);
    }

    @Test
    void testBuscarDoadorPorCpf_ComDoadorNaoEncontrado_RetornadoThrowDoadorNaoEncontradoException() {
        assertThrows(DoadorNaoEncontradoException.class, () -> {
            _doadorService.buscarDoadorPorCpf(anyString());
        });
    }

    @Test
    void testDeletarDoadorComId_ComDoadorEncontrado_RetornadoDoadorDeletado() {
        when(_doadorRepository.findById(1)).thenReturn(Optional.of(DOADOR_VALIDO));
        _doadorService.deletarDoadorComId(1);
        verify(_enderecoService, times(1)).deletarEnderecoComDoadorCpf(anyString());
        verify(_doadorRepository, times(1)).delete(DOADOR_VALIDO);
    }

    @Test
    void testDeletarDoadorComId_ComDoadorNaoEncontrado_RetornadoThrowDoadorNaoEncontradoException() {
        assertThrows(DoadorNaoEncontradoException.class, () -> {
            _doadorService.deletarDoadorComId(anyInt());
        });
    }
}