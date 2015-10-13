package com.github.yukihane.hello.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class App2Test {

    @Test
    public void testCall() {
        App2 obj = new App2();
        assertEquals("App2", obj.call());
    }
}
