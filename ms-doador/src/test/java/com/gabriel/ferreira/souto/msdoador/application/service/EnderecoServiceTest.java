package com.gabriel.ferreira.souto.msdoador.application.service;

import com.gabriel.ferreira.souto.msdoador.application.dtos.EnderecoDTO;
import com.gabriel.ferreira.souto.msdoador.domain.interfaces.IEnderecoRepository;
import com.gabriel.ferreira.souto.msdoador.domain.model.endereco.Endereco;
import com.gabriel.ferreira.souto.msdoador.infra.exceptions.EmailJaExisteException;
import com.gabriel.ferreira.souto.msdoador.infra.exceptions.EnderecoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.net.http.WebSocketHandshakeException;
import java.util.Optional;

import static com.gabriel.ferreira.souto.msdoador.common.DoadorConstants.DOADOR_REQUEST_DTO_VALIDO;
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
    void testBuscarEndereco_ComDoadorCpf_ComEnderecoValido_RetornandoEndereco(){
        when(_enderecoRepository.findByDoadorCpf(ENDERECO_VALIDO.getDoadorCpf())).thenReturn(Optional.of(ENDERECO_VALIDO));
        when(_modelMapper.map(ENDERECO_VALIDO, EnderecoDTO.class)).thenReturn(ENDERECO_DTO_VALIDO);
        EnderecoDTO result = _enderecoService.buscarEnderecoComDoadorCpf(ENDERECO_VALIDO.getDoadorCpf());

        assertNotNull(result);
        assertEquals(ENDERECO_DTO_VALIDO, result);

    }
    @Test
    void testBuscarEndereco_ComDoadorCpf_ComDoadorCpfInvalido_RetornandoEnderecoNaoEncontradoException(){
        assertThrows(EnderecoNaoEncontradoException.class, ()->{
            _enderecoService.buscarEnderecoComDoadorCpf(ENDERECO_VALIDO.getDoadorCpf());
        });
    }
    @Test
    void testAtualizarEndereco_ComEnderecoValido_RetornandoEnderecoAtualizado(){
        when(_enderecoRepository.findByDoadorCpf(ENDERECO_VALIDO.getDoadorCpf())).thenReturn(Optional.of(ENDERECO_VALIDO));
        ENDERECO_VALIDO.setEstado("Alagoas");
        ENDERECO_VALIDO.setCep("21231245");
        ENDERECO_VALIDO.setCidade("PA");
        ENDERECO_VALIDO.setBairro("Inter lagos");
        when(_enderecoRepository.save(ENDERECO_VALIDO)).thenReturn(ENDERECO_VALIDO);
        when(_modelMapper.map(ENDERECO_VALIDO, EnderecoDTO.class)).thenReturn(ENDERECO_DTO_VALIDO);

        EnderecoDTO result = _enderecoService.atualizarEndereco(ENDERECO_DTO_VALIDO, ENDERECO_VALIDO.getDoadorCpf());
        assertNotNull(result);
        assertEquals(ENDERECO_DTO_VALIDO, result);
    }
    @Test
    void testDeletarEnderecoComDoadorCpf_ComEnderecoValido_RetornandoEnderecoDeletado(){
        when(_enderecoRepository.findByDoadorCpf(ENDERECO_VALIDO.getDoadorCpf())).thenReturn(Optional.of(ENDERECO_VALIDO));
        _enderecoService.deletarEnderecoComDoadorCpf(ENDERECO_VALIDO.getDoadorCpf());
        verify(_enderecoRepository, times(1)).deleteById(ENDERECO_VALIDO.getId());
    }
}