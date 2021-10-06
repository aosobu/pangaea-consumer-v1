package com.pangaea.consumer.controller;

import com.pangaea.consumer.model.api.SubscriberRequest;
import com.pangaea.consumer.model.api.SubscriberRequestBase;
import com.pangaea.consumer.model.api.TopicRequest;
import com.pangaea.consumer.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1")
public class SubscriberController {

    private SubscriberService subscriberService;

    @PostMapping(value = "/subscribe/{topic}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SubscriberRequestBase> subscribe(@RequestBody TopicRequest request, @PathVariable String topic){
        SubscriberRequest subscriberRequest = new SubscriberRequest();
        try {
            subscriberRequest  = SubscriberRequest.with(request.getUrl(), topic);
        } catch (Exception e) {
            subscriberRequest.getErrors().add(e.getMessage());
            return new ResponseEntity<>(subscriberRequest, HttpStatus.EXPECTATION_FAILED); //TODO :: @ControllerAdvice
        }
        if(!subscriberRequest.getErrors().isEmpty())
            return new ResponseEntity<>(subscriberRequest, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(subscriberService.saveNewSubscriber(subscriberRequest), HttpStatus.OK);
    }

    @Autowired
    public void setSubscriberService(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }
}
