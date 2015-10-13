package com.github.yukihane.hello.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testCall() {
        App obj = new App();
        assertEquals("App", obj.call());
    }
}
