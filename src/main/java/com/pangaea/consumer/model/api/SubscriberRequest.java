package com.pangaea.consumer.model.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pangaea.consumer.model.Subscriber;
import com.pangaea.consumer.model.Topic;
import com.pangaea.consumer.service.SubscriberServiceImpl;
import com.pangaea.consumer.service.TopicServiceImpl;
import com.pangaea.consumer.util.UrlValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Service
public class SubscriberRequest {

    private String url;
    private String topic;
    private List<String> errors = new ArrayList<>();

    @JsonIgnore
    private static TopicServiceImpl topicService;

    @JsonIgnore
    private static SubscriberServiceImpl subscriberService;

    public static SubscriberRequest with(String url, String topic) {
        SubscriberRequest request = new SubscriberRequest();

        if(UrlValidator.urlValidator(url) && !topic.isEmpty()){
            request.setTopic(topic);
            request.setUrl(url);
            if(!alreadySubscribedToTopic(request))
                return request;
            else{
                request.setUrl(null);
                request.setTopic(null);
                request.getErrors().add("Url already subscribed to Topic! You can subscribe to another topic");
                return request;
            }
        }

        if(!UrlValidator.urlValidator(url))
            request.getErrors().add("Oops! Badly formed URL ");
        if(topic.isEmpty())
            request.getErrors().add("Oops! Topic cannot be left empty ");
        return request;
    }

    private static boolean alreadySubscribedToTopic(SubscriberRequest subscriberRequest){
        Topic topic = new Topic();
        try{
            topic = topicService.findByName(subscriberRequest.getTopic());
        }catch(Exception ex){
            //send internal mail to admin or populate self service error table on database
            subscriberRequest.getErrors().add("Topic currently unavailable for subscription! Try again later");
            return false;
        }

        if(topic != null) {
            Set<Subscriber> subscriberHashSet = topic.getSubscriberList();
            List<Subscriber> subscriberListWithIncomingUrl = subscriberService.findByUrl(subscriberRequest.getUrl());
            for(Subscriber subscriber : subscriberListWithIncomingUrl)
                if(subscriberHashSet.contains(subscriber))
                    return true;
        }
        return false;
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
