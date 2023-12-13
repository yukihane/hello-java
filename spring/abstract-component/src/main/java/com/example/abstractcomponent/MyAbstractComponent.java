package com.example.abstractcomponent;

import org.springframework.stereotype.Component;

@Component
public abstract class MyAbstractComponent {

    public String getName() {
        return getClass().getSimpleName();
    }

}
