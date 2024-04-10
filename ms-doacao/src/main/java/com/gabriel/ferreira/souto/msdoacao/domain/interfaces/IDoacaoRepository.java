package com.gabriel.ferreira.souto.msdoacao.domain.interfaces;

import com.gabriel.ferreira.souto.msdoacao.domain.model.doacao.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IDoacaoRepository extends JpaRepository<Doacao, Integer> {
    Doacao findFirstByDoadorIdOrderByIdDesc(Integer doadorId);
}
