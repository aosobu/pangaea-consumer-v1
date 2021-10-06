package com.pangaea.consumer.service.interfaces;

import org.springframework.messaging.Message;

public interface StrategyHandler {

    String getName();
    boolean isApplicable(Message<String> message);
    String executeAction(Message<String> message);
}
