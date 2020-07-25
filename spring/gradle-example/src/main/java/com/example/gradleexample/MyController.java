package com.example.gradleexample;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MyController {

    private final MyEntityMapper mapper;

    @PostMapping("/")
    public MyEntity register(@RequestBody final MyForm myForm) {
        return mapper.from(myForm);
    }
}
