package com.gabriel.ferreira.souto.msdoacao.apresentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gabriel.ferreira.souto.msdoacao.application.dtos.DoacaoDTO;
import com.gabriel.ferreira.souto.msdoacao.application.interfaces.IDoacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doacoes")
public class DoacaoController {
    private final IDoacaoService _doacaoService;

    public DoacaoController(IDoacaoService doacaoService) {
        _doacaoService = doacaoService;
    }

    @PostMapping
    public ResponseEntity<DoacaoDTO> criarDoacao(@RequestBody DoacaoDTO doacaoDTO) throws JsonProcessingException {
        DoacaoDTO doacaoDTOResult = _doacaoService.criarDoacao(doacaoDTO);
        return new ResponseEntity<>(doacaoDTOResult, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DoacaoDTO>> listarTodasAsDoacoes() {
        List<DoacaoDTO> doacoes = _doacaoService.listarTodasAsDoacao();
        return ResponseEntity.ok(doacoes);
    }

    @GetMapping(value = "/{doacaoId}")
    public ResponseEntity<DoacaoDTO> buscarDoacaoPorId(@PathVariable Integer doacaoId) {
        DoacaoDTO doacaoDTO = _doacaoService.buscarDoacaoPorId(doacaoId);
        return new ResponseEntity<>(doacaoDTO, HttpStatus.OK);
    }


}
