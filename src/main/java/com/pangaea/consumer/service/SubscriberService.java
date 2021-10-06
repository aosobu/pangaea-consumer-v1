package com.pangaea.consumer.service;

import com.pangaea.consumer.model.Subscriber;
import com.pangaea.consumer.model.Topic;
import com.pangaea.consumer.model.api.SubscriberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {

    private TopicServiceImpl topicService;
    private SubscriberServiceImpl subscriberService;

    public SubscriberRequest saveNewSubscriber(SubscriberRequest subscriberRequest){
        Topic topic = new Topic();
        String topicName = subscriberRequest.getTopic();

        topic.setName(topicName);
        topic = topicService.createNewTopic(topicName);

        Subscriber subscriber = new Subscriber();
        subscriber.setTopic(topic);
        subscriber.setUrl(subscriberRequest.getUrl());

        subscriberService.saveSubscriber(subscriber);
        return subscriberRequest;
    }

    @Autowired
    public void setTopicService(TopicServiceImpl topicService) {
        this.topicService = topicService;
    }

    @Autowired
    public void setSubscriberService(SubscriberServiceImpl subscriberService) {
        this.subscriberService = subscriberService;
    }
}
