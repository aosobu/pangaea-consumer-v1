package com.pangaea.consumer.service;

import com.pangaea.consumer.enums.NodeKey;
import com.pangaea.consumer.model.Subscriber;
import com.pangaea.consumer.model.Topic;
import com.pangaea.consumer.service.interfaces.StrategyHandler;
import com.pangaea.consumer.util.DynamicJsonParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

@Service
public class PublishTopicStategyHandler implements StrategyHandler {

    private Logger logger = LoggerFactory.getLogger(PublishTopicStategyHandler.class);
    private TopicServiceImpl topicService;
    private String result;

    @Override
    public String getName() {
        return PublishTopicStategyHandler.class.getName();
    }

    @Override
    public boolean isApplicable(Message<String> message) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(message.getPayload());
        } catch (JSONException e) {
            logger.info(" error encountered while parsing payload as json object {} " + e.getMessage());
        }
        assert jsonObject != null;
        return jsonObject.has(NodeKey.PUBLISHER_TOPIC.getNodeKey());
    }

    @Override
    public String executeAction(Message<String> message) {
        String payload = message.getPayload();

        try {
            JSONObject jsonObject = new JSONObject(payload);
            String topicFromPayload = DynamicJsonParser.getKey(jsonObject, NodeKey.PUBLISHER_TOPIC.getNodeKey());

            if(!topicFromPayload.isEmpty()){
                Topic topic = topicService.findByName(topicFromPayload);
               // Set<Subscriber> subscriberList = topic.getSubscriberList();

//                if (!topic.getSubscriberList().isEmpty()) {
//
//                    HashMap<String, Object> payloadToBePublished = new HashMap<>();
//                    payloadToBePublished.put("topic", topic);
//
//                    //remove appended topic key from payload
//                    payloadToBePublished.put("data", payload);
//
//                    String payLoadAsJsonString = DynamicJsonParser.asJsonString(payloadToBePublished);
//
//                    subscriberList.forEach(subscriber -> {
//                        logger.info(payLoadAsJsonString +  " published to " + subscriber.getUrl());
//                        result = "publish successful";
//                    });
//                    return result;
//                }
            }

        } catch (JSONException e) {
            result.concat("publish failed with exception {} " + e.getMessage());
        }

        return result;
    }

    @Autowired
    public void setTopicService(TopicServiceImpl topicService) {
        this.topicService = topicService;
    }
}
