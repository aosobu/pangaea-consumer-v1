package com.pangaea.consumer.service;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.pangaea.consumer.model.Subscriber;
import com.pangaea.consumer.model.Topic;
import com.pangaea.consumer.model.api.SubscriberRequest;
import com.pangaea.consumer.model.api.SubscriberRequestBase;
import com.pangaea.consumer.util.DynamicJsonParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        List<String> excludedFields = new ArrayList<>();
        excludedFields.add("errors");
        String subscriberRequestAsString = excludeErrorField(subscriberRequest, excludedFields);

        return recreateSubscriberRequestFromString(subscriberRequestAsString);
    }

    private String excludeErrorField(SubscriberRequest subscriberRequest, List<String> fields){
        String subscriberRequestAsString = "";
        String [] fieldsAsString = new String[fields.size()];

        for (int i = 0; i < fields.size(); i++) fieldsAsString[i] = fields.get(i);

        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Object.class, PropertyFilterMixIn.class);

        FilterProvider filters = new SimpleFilterProvider().addFilter("filter properties by name",
                                                                            SimpleBeanPropertyFilter.serializeAllExcept(
                                                                            fieldsAsString));
        ObjectWriter writer = mapper.writer(filters);
        try {
            subscriberRequestAsString = writer.writeValueAsString(subscriberRequest);
        } catch (JsonProcessingException e) {
            logger.info("error encountered while excluding errors from subscriber request" + e.getMessage());
        }
        return subscriberRequestAsString;
    }

    /**
     * TODO :: could use some more abstraction to make it dynamic; feels static
     * @param subscriberRequestAsString
     * @return
     */
    private SubscriberRequestBase recreateSubscriberRequestFromString(String subscriberRequestAsString){
        SubscriberRequestBase subscriberRequest = new SubscriberRequestBase();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(subscriberRequestAsString);
        } catch (JSONException e) {
            logger.info("Exception Parsing Message {} " + e.getMessage());
        }

        subscriberRequest.setUrl(DynamicJsonParser.getKey(jsonObject, "url"));
        subscriberRequest.setTopic(DynamicJsonParser.getKey(jsonObject, "topic"));

        return subscriberRequest;
    }

    @JsonFilter("filter properties by name")
    private static class PropertyFilterMixIn { }

    @Autowired
    public void setTopicService(TopicServiceImpl topicService) {
        this.topicService = topicService;
    }

    @Autowired
    public void setSubscriberService(SubscriberServiceImpl subscriberService) {
        this.subscriberService = subscriberService;
    }
}
