package com.epam.jwd.training.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertiesReaderTest {

    @Test
    public void test_get() {
        String expected = "Hello";
        String key = "test";
        String actual = PropertiesReader.get(key);

        Assert.assertEquals(expected, actual);
    }
}