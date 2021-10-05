package com.pangaea.consumer.controller;

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
    public String subscribe(@RequestBody String request, @PathVariable String topic){
        return subscriberService.saveNewSubscriber(request, topic);
    }

    @Autowired
    public void setSubscriberService(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }
}
