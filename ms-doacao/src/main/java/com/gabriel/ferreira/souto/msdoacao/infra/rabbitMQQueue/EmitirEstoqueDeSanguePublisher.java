package com.gabriel.ferreira.souto.msdoacao.infra.rabbitMQQueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.ferreira.souto.msdoacao.domain.model.estoque.Estoque;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmitirEstoqueDeSanguePublisher {
    private final RabbitTemplate _rabbitTemplate;
    private final Queue _queueEmitirEstoqueDeSangue;

    public EmitirEstoqueDeSanguePublisher(RabbitTemplate rabbitTemplate, Queue queueEmitirEstoqueDeSangue) {
        _rabbitTemplate = rabbitTemplate;
        _queueEmitirEstoqueDeSangue = queueEmitirEstoqueDeSangue;
    }

    public void emitirEstoqueDeSangue(Estoque dados) throws JsonProcessingException {
        String json = convertIntoJson(dados);
        _rabbitTemplate.convertAndSend(_queueEmitirEstoqueDeSangue.getActualName(), json);
    }

    private String convertIntoJson(Estoque dados) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dados);
    }
}
