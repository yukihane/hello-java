package com.github.yukihane.java.beanvalidationrest.controller;

import com.github.yukihane.java.beanvalidationrest.bean.ValidErrorEntity;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/ve")
public class ValidErrorContoller {
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String doError(@Valid @RequestBody final ValidErrorEntity entity) {
        return "ok";
    }
}
