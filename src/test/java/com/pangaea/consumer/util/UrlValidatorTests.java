package com.pangaea.consumer.util;

import org.junit.Assert;
import org.junit.Test;

public class UrlValidatorTests {

    @Test
    public void test_url_validator_passes_when_url_valid() {
        String url = "http://mysubscriber.test";
        Assert.assertTrue(UrlValidator.urlValidator(url));
    }

    @Test
    public void test_url_validator_fails_when_url_invalid() {
        String url = "http:/mysubscriber.test";
        Assert.assertFalse(UrlValidator.urlValidator(url));
    }
}
