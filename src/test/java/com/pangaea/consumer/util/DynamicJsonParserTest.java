package com.pangaea.consumer.util;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class DynamicJsonParserTest {

    @Test
    public void test_extract_key_topic_from_json_string_when_key_exists_as_json_object() {
        String key = "topic";
        String payload = "{\n" +
                "    \"name\":\"spiritcoder\",\n" +
                "    \"address\": \"clovesoftongueavenue,paradise\",\n" +
                "    \"pilgrims\": [\n" +
                "        {\n" +
                "            \"males\" : \"dayo,samuel,david\",\n" +
                "            \"female\" : \"anu,zion,ajibola\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"topic\":\"topic1\"\n" +
                "}";

        try {

            JSONObject jsonPayload = new JSONObject(payload);
            Assert.assertEquals("topic1", DynamicJsonParser.getKey(jsonPayload , key));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_extract_key_topic_from_json_string_when_key_exists_as_json_object_in_json_array() {
        String key = "males";
        String payload = "{\n" +
                "    \"name\":\"spiritcoder\",\n" +
                "    \"address\": \"clovesoftongueavenue,paradise\",\n" +
                "    \"pilgrims\": [\n" +
                "        {\n" +
                "            \"males\" : \"dayo,samuel,david\",\n" +
                "            \"female\" : \"anu,zion,ajibola\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"topic\":\"topic1\"\n" +
                "}";

        try {

            JSONObject jsonPayload = new JSONObject(payload);
            System.out.println(DynamicJsonParser.getKey(jsonPayload , key));
            Assert.assertEquals("dayo,samuel,david", DynamicJsonParser.getKey(jsonPayload , key));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
