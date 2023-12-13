package com.example.abstractcomponent;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyController {

    private final MyAbstractComponent component;

    @GetMapping
    public String index() {
        return component.getName();
    }
}
