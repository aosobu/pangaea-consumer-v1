package com.pangaea.consumer.service.interfaces;

import com.pangaea.consumer.model.api.SubscriberRequest;

public interface SaveSubscriberFilter {

    void setNextChain(SaveSubscriberFilter nextFilter);

    boolean proceed(SubscriberRequest request);
}
