package com.gabriel.ferreira.souto.msdoador.apresentation;

import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorRequestDTO;
import com.gabriel.ferreira.souto.msdoador.application.dtos.DoadorResponseDTO;
import com.gabriel.ferreira.souto.msdoador.application.interfaces.IDoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doadores")
public class DoadorController {
    private final IDoadorService _doadorService;

    @Autowired
    public DoadorController(IDoadorService doadorService) {
        _doadorService = doadorService;
    }

    @PostMapping(name = "Criar Doador")
    public ResponseEntity<DoadorResponseDTO> criarDoador(@RequestBody DoadorRequestDTO doadorRequestDTO) {
        DoadorResponseDTO doadorResponseDTO = _doadorService.criarDoador(doadorRequestDTO);
        return new ResponseEntity<>(doadorResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping(name = "Atualizar Doador", value = "/{doadorId}")
    public ResponseEntity<DoadorResponseDTO> atualizaarDoador(@RequestBody DoadorRequestDTO doadorRequestDTO,
                                                              @PathVariable Integer doadorId) {
        DoadorResponseDTO doadorResponseDTO = _doadorService.atualizarDoador(doadorRequestDTO, doadorId);
        return new ResponseEntity<>(doadorResponseDTO, HttpStatus.OK);
    }

    @GetMapping(name = "Buscar Doador", value = "/id/{doadorId}")
    public ResponseEntity<DoadorResponseDTO> buscarDoadorComId(@PathVariable Integer doadorId) {
        DoadorResponseDTO doadorResponseDTO = _doadorService.buscarDoadorComId(doadorId);
        return new ResponseEntity<>(doadorResponseDTO, HttpStatus.OK);
    }

    @GetMapping(name = "Buscar Doador Por Cpf", value = "/cpf/{cpf}")
    public ResponseEntity<DoadorResponseDTO> buscarDoadorPorCpf(@PathVariable String cpf) {
        DoadorResponseDTO doadorResponseDTO = _doadorService.buscarDoadorPorCpf(cpf);
        return new ResponseEntity<>(doadorResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(name = "Deletar Doador", value = "/{doadorId}")
    public ResponseEntity<String> deletarDoador(@PathVariable Integer doadorId) {
        _doadorService.deletarDoadorComId(doadorId);
        return new ResponseEntity<>("Doador Deletado com Sucesso", HttpStatus.OK);
    }


}
