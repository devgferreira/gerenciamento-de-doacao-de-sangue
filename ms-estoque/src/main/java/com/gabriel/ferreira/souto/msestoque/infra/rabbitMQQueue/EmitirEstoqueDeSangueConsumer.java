package com.gabriel.ferreira.souto.msestoque.infra.rabbitMQQueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.ferreira.souto.msestoque.domain.model.estoque.Estoque;
import com.gabriel.ferreira.souto.msestoque.domain.repository.IEstoqueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import java.util.Queue;

@Component
@Slf4j
public class EmitirEstoqueDeSangueConsumer {
    private final IEstoqueRepository _estoqueRepository;

    public EmitirEstoqueDeSangueConsumer(IEstoqueRepository estoqueRepository) {
        _estoqueRepository = estoqueRepository;
    }

    @RabbitListener(queues = "${mq.queues.emitir-estoque-de-sangue}")
    public void ReceberEstoqueDeSangue(@Payload String payload){
        try {
            ObjectMapper mapper = new ObjectMapper();
            Estoque result = mapper.readValue(payload, Estoque.class);
            Estoque estoque = _estoqueRepository.findByTipoSanguineo(result.getTipoSanguineo());
            Integer valorSomadoSangueMl = estoque.getSangueML() + result.getSangueML();
            estoque.setSangueML(valorSomadoSangueMl);
            _estoqueRepository.save(estoque);
        } catch (Exception e) {
            log.error("Erro ao receber solicitacao de emitir-proposta-resultado-votacao: {}", e.getMessage());
        }
    }
}
