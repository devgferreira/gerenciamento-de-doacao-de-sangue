package com.gabriel.ferreira.souto.msdoacao.infra.config.rabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${mq.queues.emitir-estoque-de-sangue}")
    private String emitirEstoqueDeSangue;

    @Bean
    public Queue queueEmitirEstoqueDeSangue() {
        return new Queue(emitirEstoqueDeSangue, true);
    }
}
