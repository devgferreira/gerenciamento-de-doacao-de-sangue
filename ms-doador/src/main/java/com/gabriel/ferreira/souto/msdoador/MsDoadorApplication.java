package com.gabriel.ferreira.souto.msdoador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsDoadorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsDoadorApplication.class, args);
    }

}
