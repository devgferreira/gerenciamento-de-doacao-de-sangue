package com.gabriel.ferreira.souto.msdoador.apresentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorRequestDTO;
import com.gabriel.ferreira.souto.msdoador.application.interfaces.IDoadorService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.gabriel.ferreira.souto.msdoador.common.DoadorConstants.DOADOR_REQUEST_DTO_VALIDO;
import static com.gabriel.ferreira.souto.msdoador.common.DoadorConstants.DOADOR_RESPONSE_DTO_VALIDO;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class DoadorControllerTest {
    @Autowired
    private MockMvc _mockMvc;
    @Autowired
    private ObjectMapper _objectMapper;
    @MockBean
    private IDoadorService _doadorService;

    @SneakyThrows
    @Test
    void testCriarDoador_QuandoDoadorValido_RetornadoDoadorCriado() {
        when(_doadorService.criarDoador(any(DoadorRequestDTO.class))).thenReturn(DOADOR_RESPONSE_DTO_VALIDO);
        _mockMvc.perform(post("/api/doadores").content(_objectMapper.writeValueAsString(DOADOR_REQUEST_DTO_VALIDO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.cpf").value(DOADOR_RESPONSE_DTO_VALIDO.getCpf()));
    }

    @SneakyThrows
    @Test
    void testBuscarDoadorComId_QuandoDoadorValido_RetornadoDoador() {
        int doadorId = 1;
        when(_doadorService.buscarDoadorComId(doadorId)).thenReturn(DOADOR_RESPONSE_DTO_VALIDO);
        _mockMvc.perform(get("/api/doadores/id/{doadorId}", doadorId))
                .andExpect(status().isOk()).andExpect(jsonPath("$.cpf").value(DOADOR_RESPONSE_DTO_VALIDO.getCpf()));
    }

    @SneakyThrows
    @Test
    void testBuscarDoadorPorCpf_QuandoDoadorValido_RetornadoDoador() {
        String cpf = "82116296072";
        when(_doadorService.buscarDoadorPorCpf(cpf)).thenReturn(DOADOR_RESPONSE_DTO_VALIDO);
        _mockMvc.perform(get("/api/doadores/cpf/{cpf}", cpf))
                .andExpect(status().isOk()).andExpect(jsonPath("$.cpf").value(DOADOR_RESPONSE_DTO_VALIDO.getCpf()));
    }

    @SneakyThrows
    @Test
    void testAtualizaarDoador_QuandoDoadorValido_RetornadoDoadorAtualizado() {
        int doadorId = 1;
        when(_doadorService.atualizarDoador(DOADOR_REQUEST_DTO_VALIDO, doadorId)).thenReturn(DOADOR_RESPONSE_DTO_VALIDO);

        _mockMvc.perform(put("/api/doadores/{doadorId}", doadorId).content(_objectMapper.writeValueAsString(DOADOR_REQUEST_DTO_VALIDO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.cpf").value(DOADOR_RESPONSE_DTO_VALIDO.getCpf()));
    }

    @SneakyThrows
    @Test
    void testDeletarDoador_QuandoDoadorValido_RetornadoDoadorDeletado() {
        int doadorId = 1;
        _doadorService.deletarDoadorComId(doadorId);

        _mockMvc.perform(delete("/api/doadores/{doadorId}", doadorId))
                .andExpect(status().isOk()).andExpect(jsonPath("$").value("Doador Deletado com Sucesso"));
    }

}