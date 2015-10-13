package com.github.yukihane.hello.java;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class TestNgAppTest {

    @Test
    public void testCall() {
        App obj = new App();
        assertEquals(obj.call(), "App");
    }
}
