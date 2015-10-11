package com.github.yukihane.hello.java.dagger2;

import javax.inject.Singleton;

import dagger.Component;

public class CoffeeApp {

    @Singleton
    @Component(modules = { DripCoffeeModule.class })
    public interface Coffee {
        CoffeeMaker maker();
    }
    //
    // public static void main(String[] args) {
    // Coffee coffee = DaggerCoffeeApp_Coffee.builder().build();
    // coffee.maker().brew();
    // }
}
