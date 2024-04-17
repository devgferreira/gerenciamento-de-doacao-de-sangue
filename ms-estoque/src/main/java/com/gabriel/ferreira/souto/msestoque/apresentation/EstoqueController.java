package com.gabriel.ferreira.souto.msestoque.apresentation;

import com.gabriel.ferreira.souto.msestoque.application.interfaces.IEstoqueService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {
    private final IEstoqueService _estoqueService;

    public EstoqueController(IEstoqueService estoqueService) {
        _estoqueService = estoqueService;
    }

}
