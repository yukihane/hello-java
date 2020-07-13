package com.example.kindsofinjection.controller;

import com.example.kindsofinjection.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * フィールドインジェクション実装サンプル。
 * フィールドに {@code @Autowired}アノテーションを付与する。
 */
@RestController
@RequestMapping("/field")
public class FieldController {

    @Autowired
    private MyService myService;

    @GetMapping
    public String greet() {
        return myService.greet();
    }
}
