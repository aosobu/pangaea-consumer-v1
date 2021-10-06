package com.pangaea.consumer.util;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.pangaea.consumer.model.api.SubscriberRequest;
import com.pangaea.consumer.model.api.SubscriberRequestBase;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Adewale S Osobu
 */
public class JsonResponseFieldExclusionUtil {

    private Logger logger = LoggerFactory.getLogger(JsonResponseFieldExclusionUtil.class);

    private String excludeErrorField(SubscriberRequest subscriberRequest, List<String> fields){
        String subscriberRequestAsString = "";
        String [] fieldsAsString = new String[fields.size()];

        for (int i = 0; i < fields.size(); i++) fieldsAsString[i] = fields.get(i);

        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Object.class, PropertyFilterMixIn.class);

        FilterProvider filters = new SimpleFilterProvider().addFilter("filter properties by name",
                                                                        SimpleBeanPropertyFilter.serializeAllExcept(
                                                                        fieldsAsString));
        ObjectWriter writer = mapper.writer(filters);
        try {
            subscriberRequestAsString = writer.writeValueAsString(subscriberRequest);
        } catch (JsonProcessingException e) {
            logger.info("error encountered while excluding errors from subscriber request" + e.getMessage());
        }
        return subscriberRequestAsString;
    }

    /**
     * TODO :: More Abstraction to improve reusability for similar use cases
     * @param subscriberRequestAsString
     * @return SubscriberRequestBase
     */
    private SubscriberRequestBase recreateSubscriberRequestFromString(String subscriberRequestAsString){
        SubscriberRequestBase subscriberRequest = new SubscriberRequestBase();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(subscriberRequestAsString);
        } catch (JSONException e) {
            logger.info("Exception Parsing Message {} " + e.getMessage());
        }

        subscriberRequest.setUrl(DynamicJsonParser.getKey(jsonObject, "url"));
        subscriberRequest.setTopic(DynamicJsonParser.getKey(jsonObject, "topic"));

        return subscriberRequest;
    }

    @JsonFilter("filter properties by name")
    private static class PropertyFilterMixIn { }
}
