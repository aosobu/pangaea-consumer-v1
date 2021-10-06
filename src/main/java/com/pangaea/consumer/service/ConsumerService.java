package com.pangaea.consumer.service;

import com.pangaea.consumer.service.interfaces.StrategyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ConsumerService {

    private Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    private List<StrategyHandler> strategyHandlerList;

//    @KafkaListener(topics = "${kafka.topic.request-topic}" , containerFactory = "kafkaListenerContainerFactory")
//    @SendTo()
//    public String receive(Message<String> message) {
//        String result = "";
//        for (StrategyHandler handler : strategyHandlerList) {
//            if (handler.isApplicable(message)) {
//                result = handler.executeAction(message);
//                logger.info(handler.getName() + " invoked on payload {} " + message.getPayload());
//                break;
//            }
//        }
//
//        return result;
//    }

    @Resource(name = "strategyHandlerList")
    public void setStrategyHandlerHashMap(List<StrategyHandler> strategyHandlerList) {
        this.strategyHandlerList = strategyHandlerList;
    }
}
