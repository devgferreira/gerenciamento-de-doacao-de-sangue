package com.gabriel.ferreira.souto.msestoque.domain.repository;

import com.gabriel.ferreira.souto.msestoque.domain.model.estoque.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstoqueRepository extends JpaRepository<Integer, Estoque> {
}
