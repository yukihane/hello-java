package com.example.springboot.controller;

import com.example.springboot.entity.MyEntity;
import com.example.springboot.property.MyProperties;
import com.example.springboot.repository.MyRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link org.springframework.stereotype.Controller} は、 (Model-View-Controller
 * パターンで言うところの) Controllerを示すステレオタイプ。 {@link RestController}
 * はそのうち、レスポンスを(ページでなく) {@link ResponseBody} で返すもの。
 */
@RestController
@RequestMapping("/greeting")
public class MyController {

    @Autowired
    private MyRepository repository;

    @Autowired
    private MyProperties prop;

    /**
     * {@link GetMapping} は javadoc に説明がある通り、
     * {@code @RequestMapping(method = RequestMethod.GET)} と等価です。 (ので、
     * {@link RequestMapping} の javadoc も参照すべきです。)
     */
    @GetMapping(path = "/hello/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MyResponse hello(final @PathVariable String id) {
        final Optional<MyEntity> entity = repository.find(Long.valueOf(id));
        final String greeting = prop.getGreeting();

        final MyResponse resp = new MyResponse(greeting + ", " + entity.map(MyEntity::getName).orElse("unknown") + "!");
        return resp;
    }

    @PutMapping(path = "/entity", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MyEntity create(@RequestBody final MyEntity entity) {
        final MyEntity ret = repository.insert(entity);
        return ret;
    }
}
