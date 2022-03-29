package com.spring.rabbitmq.resources;

import com.spring.rabbitmq.services.SendAndReceiveMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@AllArgsConstructor
public class MessageResource {

    private final SendAndReceiveMessageService sendAndReceiveMessageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String sendAndReceiveMessage() {
        sendAndReceiveMessageService.execute();
        return "";
    }
}
