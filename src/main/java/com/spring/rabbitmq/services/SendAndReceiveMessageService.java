package com.spring.rabbitmq.services;

import com.spring.rabbitmq.config.QueueConfig;
import com.spring.rabbitmq.domain.Message;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class SendAndReceiveMessageService {

    private final AsyncRabbitTemplate asyncRabbitTemplate;

    @SneakyThrows
    public void execute() {
        ListenableFuture<Message> listenableFuture = asyncRabbitTemplate.convertSendAndReceiveAsType(QueueConfig.EXCHANGE_MESSAGES, QueueConfig.ROUTING_KEY_MESSAGES, 1, new ParameterizedTypeReference<>() {});

        Message value = Objects.requireNonNull(listenableFuture).get();
        log.info(value.toString());
    }
}
