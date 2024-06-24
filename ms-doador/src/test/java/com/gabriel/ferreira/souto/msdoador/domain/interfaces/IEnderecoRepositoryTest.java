package com.gabriel.ferreira.souto.msdoador.domain.interfaces;
import static com.gabriel.ferreira.souto.msdoador.common.EnderecoConstants.*;
import static org.junit.jupiter.api.Assertions.*;

import com.gabriel.ferreira.souto.msdoador.domain.model.endereco.Endereco;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class IEnderecoRepositoryTest {
    @Autowired
    private IEnderecoRepository _enderecoRepository;

    @Test
    void testEndereco_QuandoBuscar_RetorandoEnderecoSalvo(){
        Endereco result = _enderecoRepository.save(ENDERECO_VALIDO);

        assertNotNull(result);
        assertTrue(result.getId() > 0);
        assertEquals(ENDERECO_VALIDO, result);
    }
}
