package com.example.springboot.bean;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeScopeBean {

    private int value;

    @PostConstruct
    public void init() {
        value = new Random().nextInt();
        log.info("PrototypeScopeBean is instantiated. value: {}", value);
    }
}
