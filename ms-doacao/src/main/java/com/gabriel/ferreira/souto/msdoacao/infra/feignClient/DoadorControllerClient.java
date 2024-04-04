package com.gabriel.ferreira.souto.msdoacao.infra.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "ms-doador", path = "/doadores")
public interface DoadorControllerClient {

}
