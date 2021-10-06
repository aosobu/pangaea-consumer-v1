package com.pangaea.consumer.controller;

import com.pangaea.consumer.model.api.SubscriberRequest;
import com.pangaea.consumer.service.SubscriberService;
import com.pangaea.consumer.util.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1")
public class SubscriberController {

    private SubscriberService subscriberService;

    @PostMapping(value = "/subscribe/{topic}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SubscriberRequest subscribe(@RequestBody String request, @PathVariable String topic){
        SubscriberRequest subscriberRequest = null;
        try {
            subscriberRequest  = SubscriberRequest.with(request, topic);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        if(!subscriberRequest.getErrors().isEmpty())
            return subscriberRequest;
        return subscriberService.saveNewSubscriber(subscriberRequest);
    }

    @Autowired
    public void setSubscriberService(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }
}
