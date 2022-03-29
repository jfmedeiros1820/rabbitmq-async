package com.spring.rabbitmq.listeners;

import com.spring.rabbitmq.config.QueueConfig;
import com.spring.rabbitmq.domain.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = QueueConfig.QUEUE_MESSAGES)
    public Message processMessage(Integer value) {
        Message message = new Message();
        message.setId(value.toString());
        message.setMessage("Success");
        return message;
    }
}
