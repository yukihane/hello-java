package com.github.yukihane.hello.java.dagger2;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { DripCoffeeModule.class })
public interface Coffee {
    CoffeeMaker maker();
}
