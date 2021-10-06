package com.pangaea.consumer.service.interfaces;


import com.pangaea.consumer.model.Subscriber;

import java.util.List;

public interface SubscriberService {
    Subscriber saveSubscriber(Subscriber subscriber);
    List<Subscriber> findByUrl(String url);
    List<Subscriber> findAllByUrlAndTopicId(String url, Long topicId);
}
