package com.pangaea.consumer.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1")
public class TopicController {



    @PostMapping(value = "/subscribe/{topic}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String subscribe(@RequestBody String request, @PathVariable String topic){

        return null;
    }

}
