package com.github.yukihane.hello.java.dagger2;

public class CoffeeTest {

    public static void main(String[] args) {
        Coffee coffee = DaggerCoffee.builder().build();
        coffee.maker().brew();
    }
}
