package com.pangaea.consumer.service;

import com.pangaea.consumer.model.Subscriber;
import com.pangaea.consumer.model.Topic;
import com.pangaea.consumer.repository.TopicRepository;
import com.pangaea.consumer.service.interfaces.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;

    @Override
    public Topic findByName(String name) {
         return topicRepository.findByName(name);
    }

    @Override
    public Topic createNewTopic(String name) {
        return topicRepository.save(buildTopic(name));
    }

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    private Topic buildTopic(String name){

        Topic topic = new Topic();
        topic.setName(name);

        return topic;
    }
}
