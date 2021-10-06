package com.pangaea.consumer.service;


import com.pangaea.consumer.model.Subscriber;
import com.pangaea.consumer.model.Topic;
import com.pangaea.consumer.model.api.SubscriberRequest;
import com.pangaea.consumer.model.api.SubscriberRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {

    private TopicServiceImpl topicService;
    private SubscriberServiceImpl subscriberService;
    private Logger logger = LoggerFactory.getLogger(SubscriberService.class);

    public SubscriberRequestBase saveNewSubscriber(SubscriberRequest subscriberRequest){
        Topic topic = new Topic();
        String topicName = subscriberRequest.getTopic();

        topic.setName(topicName);
        topic = topicService.findByName(topicName);

        Subscriber subscriber = new Subscriber();
        subscriber.setTopic(topic);
        subscriber.setUrl(subscriberRequest.getUrl());

        subscriberService.saveSubscriber(subscriber);

        SubscriberRequestBase subscriberRequestBase = new SubscriberRequestBase();
        subscriberRequestBase.setUrl(subscriberRequest.getUrl());
        subscriberRequestBase.setTopic(subscriberRequest.getTopic());

        return subscriberRequestBase;
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
