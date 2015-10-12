package com.github.yukihane.hello.java.dagger2;

import org.junit.Test;

public class CoffeeTest {

    @Test
    public void testMaker() {
        Coffee coffee = DaggerCoffee.builder().build();
        coffee.maker().brew();
    }
}
