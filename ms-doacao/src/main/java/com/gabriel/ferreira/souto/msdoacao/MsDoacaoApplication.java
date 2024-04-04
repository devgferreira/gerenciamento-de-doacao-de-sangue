package com.gabriel.ferreira.souto.msdoacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsDoacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsDoacaoApplication.class, args);
	}

}
