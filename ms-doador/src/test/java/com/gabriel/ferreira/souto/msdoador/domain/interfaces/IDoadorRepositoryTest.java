package com.gabriel.ferreira.souto.msdoador.domain.interfaces;

import com.gabriel.ferreira.souto.msdoador.domain.enums.Genero;
import com.gabriel.ferreira.souto.msdoador.domain.model.doador.Doador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class IDoadorRepositoryTest {

    @Autowired
    private IDoadorRepository _doadorRepository;

    @Test
    void testDoador_QuandoSalvar_RetornandoDoadorSalvo(){

        Doador doador0 = new Doador("82116296072", "Gabriel", "gabrie@gmail.com",
                new Date(), Genero.M, 60, "B-");

        Doador result = _doadorRepository.save(doador0);

        assertNotNull(result);
        assertTrue(result.getId() > 0);
        assertEquals(doador0, result);
    }
    @Test
    void testDoador_QuandoFindById_RetornandoDoador(){
        Doador doador0 = new Doador("82116296072", "Gabriel", "gabrie@gmail.com",
                new Date(), Genero.M, 60, "B-");

        _doadorRepository.save(doador0);
        Doador result = _doadorRepository.findById(doador0.getId()).get();

        assertNotNull(result);
        assertEquals(doador0.getId(), result.getId());
    }
    @Test
    void testDoador_QuandoFindByCpf_RetornandoDoador(){
        Doador doador0 = new Doador("82116296072", "Gabriel", "gabrie@gmail.com",
                new Date(), Genero.M, 60, "B-");

        _doadorRepository.save(doador0);
        Doador result = _doadorRepository.findByCpf(doador0.getCpf()).get();

        assertNotNull(result);
        assertEquals(doador0.getCpf(), result.getCpf());
    }
    @Test
    void testDoador_QuandoFindByEmail_RetornandoDoador(){
        Doador doador0 = new Doador("82116296072", "Gabriel", "gabrie@gmail.com",
                new Date(), Genero.M, 60, "B-");
        _doadorRepository.save(doador0);
        Doador result = _doadorRepository.findByEmail(doador0.getEmail()).get();

        assertNotNull(result);
        assertEquals(doador0.getEmail(), result.getEmail());
    }
    @Test
    void testDoador_QuandoAtualizarDoador_RetornandoDoadorAtualizado(){
        Doador doador0 = new Doador("82116296072", "Gabriel", "gabrie@gmail.com",
                new Date(), Genero.M, 60, "B-");

        _doadorRepository.save(doador0);
        Doador doadorSaved = _doadorRepository.findById(doador0.getId()).get();
        doadorSaved.setNome("Rafael");
        doadorSaved.setEmail("rafel@gmail.com");
        Doador result = _doadorRepository.save(doadorSaved);

        assertNotNull(result);
        assertEquals("Rafael", result.getNome());
        assertEquals("rafel@gmail.com", result.getEmail());
    }
}