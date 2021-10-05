package com.pangaea.consumer.model.api;

import com.pangaea.consumer.util.UrlValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class SubscriberRequest {

    private static Logger logger = LoggerFactory.getLogger(SubscriberRequest.class);
    private String url;
    private String topic;

    public static SubscriberRequest with(String url, String topic) throws Exception {
        SubscriberRequest request= new SubscriberRequest();
        if(UrlValidator.urlValidator(url) && !topic.isEmpty()){
            request.setTopic(topic);
            request.setUrl(url);
        }
        return request;
    }
}
