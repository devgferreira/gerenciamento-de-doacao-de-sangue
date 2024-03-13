package com.gabriel.ferreira.souto.msdoador.domain.interfaces;

import com.gabriel.ferreira.souto.msdoador.domain.model.doador.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoadorRepository extends JpaRepository<Doador, Integer> {
}
