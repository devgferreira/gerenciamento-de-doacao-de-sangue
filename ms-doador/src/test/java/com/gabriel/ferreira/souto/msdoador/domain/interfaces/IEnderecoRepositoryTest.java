package com.gabriel.ferreira.souto.msdoador.domain.interfaces;

import com.gabriel.ferreira.souto.msdoador.domain.model.endereco.Endereco;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static com.gabriel.ferreira.souto.msdoador.common.EnderecoConstants.ENDERECO_VALIDO;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IEnderecoRepositoryTest {
    @Autowired
    private IEnderecoRepository _enderecoRepository;

    @Test
    void testEndereco_QuandoBuscar_RetorandoEnderecoSalvo() {
        Endereco result = _enderecoRepository.save(ENDERECO_VALIDO);

        assertNotNull(result);
        assertTrue(result.getId() > 0);
    }

    @Test
    void testEndereco_QuandoBuscarEnderecoPorId_RetorandoEndereco() {
        _enderecoRepository.save(ENDERECO_VALIDO);
        Endereco result = _enderecoRepository.findByDoadorCpf(ENDERECO_VALIDO.getDoadorCpf()).get();

        assertNotNull(result);
        assertTrue(result.getId() > 0);
        assertEquals(ENDERECO_VALIDO.getDoadorCpf(), result.getDoadorCpf());
    }

    @Test
    void testEndereco_QuandoUpdate_RetorandoEnderecoAtualizado() {
        _enderecoRepository.save(ENDERECO_VALIDO);
        Endereco enderecoSavalvado = _enderecoRepository.findByDoadorCpf(ENDERECO_VALIDO.getDoadorCpf()).get();
        enderecoSavalvado.setBairro("XYB");
        enderecoSavalvado.setEstado("LSC");
        enderecoSavalvado.setCidade("PQE");
        enderecoSavalvado.setCep("1231245");
        enderecoSavalvado.setDoadorCpf("12312457891011");
        Endereco result = _enderecoRepository.save(enderecoSavalvado);

        assertNotNull(result);
        assertTrue(result.getId() > 0);
        assertEquals("XYB", result.getBairro());
        assertEquals("LSC", result.getEstado());
        assertEquals("PQE", result.getCidade());
        assertEquals("1231245", result.getCep());
        assertEquals("12312457891011", result.getDoadorCpf());
    }

    @Test
    void testEndereco_QuandoDeletePorId_RetorandoEnderecoDeletado() {
        _enderecoRepository.save(ENDERECO_VALIDO);
        _enderecoRepository.deleteById(ENDERECO_VALIDO.getId());

        Optional<Endereco> endereco = _enderecoRepository.findByDoadorCpf("82134196009");

        assertTrue(endereco.isEmpty());

    }
}
