package com.pangaea.consumer.model;

import org.junit.Assert;
import org.junit.Test;

public class SubscriberTest {


    @Test
    public void test_subscriber_hashcode_implementation_asserts_true_when_object_is_same() {
        Subscriber subscriber = new Subscriber();
        Subscriber subscriberClone = new Subscriber();

        subscriber.setUrl("http://mysubscriber.test");
        subscriberClone.setUrl("http://mysubscriber.test");

        Assert.assertEquals(subscriber, subscriberClone);
    }

    @Test
    public void test_subscriber_hashcode_implementation_asserts_true_when_object_is_different() {
        Subscriber subscriber = new Subscriber();
        Subscriber subscriberClone = new Subscriber();

        subscriber.setUrl("http://mysubscribers.test");
        subscriberClone.setUrl("http://mysubscriber.test");

        Assert.assertEquals(subscriber, subscriberClone);
    }
}
