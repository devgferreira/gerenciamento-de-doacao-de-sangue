package com.gabriel.ferreira.souto.msdoador.domain.interfaces;

import com.gabriel.ferreira.souto.msdoador.domain.model.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEnderecoRepository extends JpaRepository<Endereco, Integer> {
    Optional<Endereco> findEnderecoByDoadorId(Integer doadorId);
}
