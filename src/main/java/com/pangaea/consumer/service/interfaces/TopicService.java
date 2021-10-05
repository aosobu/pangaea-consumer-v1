package com.pangaea.consumer.service.interfaces;

import com.pangaea.consumer.model.Topic;

public interface TopicService {
    Topic findByName(String name);
    Topic createNewTopic(String name);
}
