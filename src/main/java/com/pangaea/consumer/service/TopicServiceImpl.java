package com.pangaea.consumer.service;

import com.pangaea.consumer.model.Topic;
import com.pangaea.consumer.repository.TopicRepository;
import com.pangaea.consumer.service.interfaces.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;

    @Override
    public Topic findByName(String name) {
        return topicRepository.findByName(name);
    }

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }
}
