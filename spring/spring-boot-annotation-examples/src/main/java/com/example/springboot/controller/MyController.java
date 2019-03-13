package com.example.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entity.MyEntity;
import com.example.springboot.property.MyProperties;
import com.example.springboot.repository.MyRepository;

@RestController
@RequestMapping("/rest")
public class MyController {

    @Autowired
    private MyRepository repository;

    @Autowired
    private MyProperties prop;

    /**
     * {@link GetMapping} は javadoc に説明がある通り、
     * {@code @RequestMapping(method = RequestMethod.GET)} と等価。
     */
    @GetMapping(path = "/hello/{id}")
    public MyResponse hello(final @PathVariable String id) {
        final Optional<MyEntity> entity = repository.find(Long.valueOf(id));
        final String greeting = prop.getGreeting();

        final MyResponse resp = new MyResponse(greeting + ", " + entity.map(MyEntity::getName).orElse("unknown") + "!");
        return resp;
    }
}
