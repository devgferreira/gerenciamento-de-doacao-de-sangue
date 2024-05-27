package com.gabriel.ferreira.souto.mscloudgeteway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
public class MsCloudGetewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCloudGetewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/doadores/**").uri("lb://ms-doador"))
                .route(r -> r.path("/api/doacoes/**").uri("lb://ms-doacao"))
                .route(r -> r.path("/api/estoques/**").uri("lb://ms-estoque"))
                .build();
    }
}
