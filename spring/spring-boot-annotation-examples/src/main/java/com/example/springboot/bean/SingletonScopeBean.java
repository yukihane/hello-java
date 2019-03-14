package com.example.springboot.bean;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link Component} を付けると、Springがスキャンしインジェクション可能なクラスであることを自動で検知します。
 * (CDIとは異なり)デフォルトのスコープ({@link org.springframework.context.annotation.Scope})はシングルトン、
 * プロキシモード({@link org.springframework.context.annotation.ScopedProxyMode})は<code>NO</code>です。
 */
@Slf4j
@Getter
@Component
public class SingletonScopeBean {

    private int value;

    @PostConstruct
    public void init() {
        value = new Random().nextInt();
        log.info("SingletonScopeBean is instantiated. value: {}", value);
    }
}
