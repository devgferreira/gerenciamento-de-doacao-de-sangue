package com.gabriel.ferreira.souto.msdoador.application.service;

import com.gabriel.ferreira.souto.msdoador.application.dtos.EnderecoDTO;
import com.gabriel.ferreira.souto.msdoador.domain.interfaces.IEnderecoRepository;
import com.gabriel.ferreira.souto.msdoador.domain.model.endereco.Endereco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.net.http.WebSocketHandshakeException;
import java.util.Optional;

import static com.gabriel.ferreira.souto.msdoador.common.EnderecoConstants.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService _enderecoService;
    @Mock
    private IEnderecoRepository _enderecoRepository;
    @Mock
    private ModelMapper _modelMapper;

    @Test
    void testcriarEndereco_ComEnderecoValido_RetornadoEndereco(){
        ENDERECO_VALIDO.setDoadorCpf("123");

        when(_modelMapper.map(ENDERECO_DTO_VALIDO, Endereco.class)).thenReturn(ENDERECO_VALIDO);
        when(_enderecoRepository.save(ENDERECO_VALIDO)).thenReturn(ENDERECO_VALIDO);
        when(_modelMapper.map(ENDERECO_VALIDO, EnderecoDTO.class)).thenReturn(ENDERECO_DTO_VALIDO);

        EnderecoDTO result = _enderecoService.criarEndereco(ENDERECO_DTO_VALIDO, ENDERECO_VALIDO.getDoadorCpf());

        assertNotNull(result);
        assertEquals(ENDERECO_DTO_VALIDO, result);

    }
    @Test
    void testBuscarEnderecoComDoadorCpf_ComEnderecoValido_RetornaandoEndereco(){
        when(_enderecoRepository.findByDoadorCpf(ENDERECO_VALIDO.getDoadorCpf())).thenReturn(Optional.of(ENDERECO_VALIDO));
        when(_modelMapper.map(ENDERECO_VALIDO, EnderecoDTO.class)).thenReturn(ENDERECO_DTO_VALIDO);
        EnderecoDTO result = _enderecoService.buscarEnderecoComDoadorCpf(ENDERECO_VALIDO.getDoadorCpf());

        assertNotNull(result);
        assertEquals(ENDERECO_DTO_VALIDO, result);

    }
}