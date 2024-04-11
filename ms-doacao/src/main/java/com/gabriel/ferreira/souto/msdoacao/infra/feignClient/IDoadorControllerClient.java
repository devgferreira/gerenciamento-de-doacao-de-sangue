package com.gabriel.ferreira.souto.msdoacao.infra.feignClient;

import com.gabriel.ferreira.souto.msdoacao.domain.model.doador.DoadorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-doador", path = "/api/doadores")
public interface IDoadorControllerClient {
    @GetMapping(value = "cpf/{cpf}")
    DoadorResponse buscarDoadorPorCpf(@PathVariable String cpf);
}
