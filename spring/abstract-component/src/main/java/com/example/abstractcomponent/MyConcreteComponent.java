package com.example.abstractcomponent;

import org.springframework.stereotype.Component;

@Component
public class MyConcreteComponent extends MyAbstractComponent {
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

}
