package com.pangaea.consumer.service;

import com.pangaea.consumer.enums.NodeKey;
import com.pangaea.consumer.util.DynamicJsonParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {

    @KafkaListener(topics = "${kafka.topic.request-topic}" , containerFactory = "kafkaListenerContainerFactory")
    @SendTo()
    public String receive(Message<String> message) {
        String payload = message.getPayload();
        try {
            JSONObject jsonObject = new JSONObject(payload);
            String topic = DynamicJsonParser.getKey(jsonObject, NodeKey.PUBLISHER_TOPIC.getNodeKey());

            if(!topic.isEmpty()){

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "publish successful";
    }


    //retrieve subscriber list as per topic
    // send message to subscriber list
    // revert feedback to producer-consumer on request-reply topic
}
