package com.pangaea.consumer.service;

import com.pangaea.consumer.model.Subscriber;
import com.pangaea.consumer.model.Topic;
import com.pangaea.consumer.model.api.SubscriberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriberService {


    private TopicServiceImpl topicService;
    private SubscriberServiceImpl subscriberService;

    public String saveNewSubscriber(SubscriberRequest subscriberRequest){
        Topic topic = topicService.findByName(subscriberRequest.getTopic());
        if(topic == null){
            topic = topicService.createNewTopic(subscriberRequest.getTopic());
        }

        if(topic.getSubscriberList().contains(subscriberRequest.getUrl())){

        }
        // check duplicate subscriber
        // find by topic
        // check if subscriber exists in topic list
        // if subscriber exists return duplicate subscription

        //else

        // save new subscriber url to topic
        {
            return null;
        }
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
