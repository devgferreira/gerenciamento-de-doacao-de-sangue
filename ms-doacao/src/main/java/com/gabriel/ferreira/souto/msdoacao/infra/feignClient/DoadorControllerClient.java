package com.gabriel.ferreira.souto.msdoacao.infra.feignClient;

import com.gabriel.ferreira.souto.msdoacao.domain.model.doador.DoadorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-doador", path = "/doadores")
public interface DoadorControllerClient {
    @GetMapping(value = "/{doadorId}")
    ResponseEntity<DoadorResponse> buscarDoadorComId(@PathVariable Integer doadorId);
}
