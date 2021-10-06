package com.pangaea.consumer.model;

import org.junit.Assert;
import org.junit.Test;

public class SubscriberTest {

    @Test
    public void test_subscriber_object_comparison_passes_when_url_and_topic_is_same() {
        Subscriber subscriber = new Subscriber();
        Subscriber subscriberClone = new Subscriber();

        Topic topic = new Topic();
        topic.setName("pangaea");

        subscriber.setUrl("http://mysubscriber.test");
        subscriber.setTopic(topic);

        subscriberClone.setUrl("http://mysubscriber.test");
        subscriberClone.setTopic(topic);

        Assert.assertEquals(subscriber, subscriberClone);
    }

    @Test
    public void test_subscriber_object_comparison_fails_when_url_and_topic_is_different() {
        Subscriber subscriber = new Subscriber();
        Subscriber subscriberClone = new Subscriber();

        Topic topic = new Topic();
        topic.setName("fiverr");
        topic.setId(1L);

        Topic topicClone = new Topic();
        topicClone.setName("fiver");
        topicClone.setId(10L);

        subscriber.setUrl("http://mysubscriber.test");
        subscriber.setTopic(topic);

        subscriberClone.setUrl("http://mysubscriber.test");
        subscriberClone.setTopic(topicClone);

        Assert.assertEquals(subscriber, subscriberClone);
    }
}
