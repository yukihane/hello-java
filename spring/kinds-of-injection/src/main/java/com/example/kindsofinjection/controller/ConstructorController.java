package com.example.kindsofinjection.controller;

import com.example.kindsofinjection.service.MyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * コンストラクタインジェクションを行ったコントローラ実装サンプル。
 * Lombokの {@code @RequiredArgsConstructor} と {@code final}フィールドを組み合わせることで
 * 明示的にコンストラクタを書かずに済む。
 */
@RestController
@RequestMapping("/constructor")
@RequiredArgsConstructor
public class ConstructorController {

    private final MyService myService;

    @GetMapping
    public String greet() {
        return myService.greet();
    }
}
