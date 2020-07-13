package com.example.kindsofinjection.controller;

import com.example.kindsofinjection.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * セッターインジェクション実装サンプル。
 * setterに{@code @Autowired}を付与する。
 */
@RestController
@RequestMapping("/setter")
public class SetterController {

    private MyService myService;

    @Autowired
    public void setMyService(final MyService myService) {
        this.myService = myService;
    }

    // Lombokを使って次のように書くことも可能
    //    @Setter(onMethod = @__(@Autowired))
    //    private MyService myService;

    @GetMapping
    public String greet() {
        return myService.greet();
    }
}
