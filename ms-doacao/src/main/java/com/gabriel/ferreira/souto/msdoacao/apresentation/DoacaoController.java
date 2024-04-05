package com.gabriel.ferreira.souto.msdoacao.apresentation;

import com.gabriel.ferreira.souto.msdoacao.application.dtos.DoacaoDTO;
import com.gabriel.ferreira.souto.msdoacao.application.interfaces.IDoacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class DoacaoController {
    private final IDoacaoService _doacaoService;

    public DoacaoController(IDoacaoService doacaoService) {
        _doacaoService = doacaoService;
    }

    @PostMapping
    public ResponseEntity<DoacaoDTO> criarDoacao(@RequestBody DoacaoDTO doacaoDTO){
        DoacaoDTO doacaoDTOResult = _doacaoService.criarDoacao(doacaoDTO);
        return new ResponseEntity<>(doacaoDTOResult, HttpStatus.CREATED);
    }

}
