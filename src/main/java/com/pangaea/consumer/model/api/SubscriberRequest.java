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
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Service
public class SubscriberRequest extends SubscriberRequestBase {

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
                request.getErrors().add("Url already subscribed to Topic! You can subscribe to another topic");
                return request;
            }
        }

        if(!UrlValidator.urlValidator(url))
            request.getErrors().add("Oops! Badly formed URL ");
        if( Objects.isNull(topic) || topic.isEmpty())
            request.getErrors().add("Oops! Topic cannot be left empty ");
        return request;
    }

    private static boolean alreadySubscribedToTopic(SubscriberRequest subscriberRequest){
        Topic topic;
        try{
            topic = topicService.findByName(subscriberRequest.getTopic());
            if(Objects.isNull(topic)) {
                subscriberRequest.getErrors().add("Topic currently not unavailable for subscription! Try again later");
                return false;
            }
        }catch(Exception ex){
            //send internal mail to admin or populate self service error table on database
            subscriberRequest.getErrors().add("Topic currently not unavailable for subscription! Try again later");
            return false;
        }

        List<Subscriber> subscriberList =  subscriberService.findAllByUrlAndTopicId(subscriberRequest.getUrl(), topic.getId());
        if(subscriberList.size() == 1) return true;

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
