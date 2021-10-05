package com.pangaea.consumer.service.interfaces;

import com.pangaea.consumer.model.Subscriber;
import com.pangaea.consumer.model.api.SubscriberRequest;
import com.pangaea.consumer.repository.SubscriberRepository;

public interface SaveSubscriberFilter {

    void setNextChain(SaveSubscriberFilter nextFilter);

    boolean proceed(SubscriberRequest request);
}
