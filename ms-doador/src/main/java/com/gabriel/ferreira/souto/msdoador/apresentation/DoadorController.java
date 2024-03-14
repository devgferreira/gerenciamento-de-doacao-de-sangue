package com.gabriel.ferreira.souto.msdoador.apresentation;

import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorRequestDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorResponseDTO;
import com.gabriel.ferreira.souto.msdoador.application.interfaces.IDoadorService;
import com.gabriel.ferreira.souto.msdoador.domain.model.doador.response.DoadorResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ResponseEntity<DoadorResponseDTO> criarDoador(@RequestBody DoadorRequestDTO doadorRequestDTO){
        DoadorDTO doadorDTO = _doadorService.criarDoador(doadorRequestDTO);
        DoadorResponseDTO doadorResponseDTO = new DoadorResponseDTO(doadorDTO);
        doadorResponseDTO.setEndereco(doadorRequestDTO.getEndereco());
        return new ResponseEntity<>(doadorResponseDTO, HttpStatus.CREATED);
    }
}
