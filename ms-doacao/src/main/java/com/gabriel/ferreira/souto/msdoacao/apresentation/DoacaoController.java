package com.gabriel.ferreira.souto.msdoacao.apresentation;

import com.gabriel.ferreira.souto.msdoacao.application.interfaces.IDoacaoService;
import com.gabriel.ferreira.souto.msdoacao.domain.interfaces.IDoacaoRepository;

public class DoacaoController {
    private final IDoacaoService _doacaoService;

    public DoacaoController(IDoacaoService doacaoService) {
        _doacaoService = doacaoService;
    }
}
