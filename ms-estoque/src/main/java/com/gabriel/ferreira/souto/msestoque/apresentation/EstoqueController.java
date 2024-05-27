package com.gabriel.ferreira.souto.msestoque.apresentation;

import com.gabriel.ferreira.souto.msestoque.application.dto.EstoqueDTO;
import com.gabriel.ferreira.souto.msestoque.application.interfaces.IEstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {
    private final IEstoqueService _estoqueService;

    public EstoqueController(IEstoqueService estoqueService) {
        _estoqueService = estoqueService;
    }

    @GetMapping(value = "/{estoqueId}")
    public ResponseEntity<EstoqueDTO> buscarEstoquePorId(@PathVariable Integer estoqueId) {
        EstoqueDTO estoqueDTO = _estoqueService.buscarEstoquePorId(estoqueId);
        return new ResponseEntity<>(estoqueDTO, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<EstoqueDTO>> bsucarTodosOsEstoques() {
        List<EstoqueDTO> estoquesDTO = _estoqueService.bsucarTodasOsEstoques();
        return new ResponseEntity<>(estoquesDTO, HttpStatus.OK);
    }
}
