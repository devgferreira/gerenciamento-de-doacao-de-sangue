package com.gabriel.ferreira.souto.msdoador.domain.interfaces;

import com.gabriel.ferreira.souto.msdoador.domain.model.doador.Doador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static com.gabriel.ferreira.souto.msdoador.common.DoadorConstants.*;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class IDoadorRepositoryTest {

    @Autowired
    private IDoadorRepository _doadorRepository;

    @Test
    void testDoador_QuandoSalvar_RetornandoDoadorSalvo(){
        Doador result = _doadorRepository.save(DOADOR_VALIDO);

        assertNotNull(result);
        assertTrue(result.getId() > 0);
        assertEquals(DOADOR_VALIDO, result);
    }
    @Test
    void testDoador_QuandoFindById_RetornandoDoador(){
        _doadorRepository.save(DOADOR_VALIDO);
        Doador result = _doadorRepository.findById(DOADOR_VALIDO.getId()).get();
        assertNotNull(result);
        assertEquals(DOADOR_VALIDO.getId(), result.getId());
    }

}