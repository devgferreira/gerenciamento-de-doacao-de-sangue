package com.gabriel.ferreira.souto.msdoador.apresentation;

import com.gabriel.ferreira.souto.msdoador.application.interfaces.IDoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doadores")
public class DoadorController {
    private final IDoadorService _doadorService;

    @Autowired
    public DoadorController(IDoadorService doadorService) {
        _doadorService = doadorService;
    }
}
