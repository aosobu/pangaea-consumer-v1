package com.pangaea.consumer.model.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubscriberRequestBase {
    private String url;
    private String topic;
}
