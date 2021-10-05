package com.pangaea.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {

//    @KafkaListener(topics = "${kafka.topic.request-topic}" , containerFactory = "kafkaListenerContainerFactory")
//    @SendTo()
//    public String receive(Message<String> message) {
//        String payload = message.getPayload();
//        System.out.println(payload);
//        return "Jesus Is Lord!!!";
//    }
}
