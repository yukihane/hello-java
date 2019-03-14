package com.example.springboot.bean;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 自分のライフサイクル(ここでは
 * {@link ConfigurableBeanFactory#SCOPE_PROTOTYPE})より長いオブジェクト(ここでは
 * {@link ConfigurableBeanFactory#SCOPE_SINGLETON} である
 * {@link com.example.springboot.property.service.MyServiceImpl})にインジェクションする場合、
 * {@code proxyMode}をON(すなわち {@link ScopedProxyMode#TARGET_CLASS} あるいは
 * {@link ScopedProxyMode#INTERFACES})
 * にしなければ意図した挙動にならない。 保持する側が消滅するまで同じオブジェクトを参照し続けるため。
 *
 */
@Slf4j
@Getter
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PrototypeScopeProxyBean {

    private int value;

    @PostConstruct
    public void init() {
        value = new Random().nextInt();
        log.info("PrototypeScopeProxyBean is instantiated. value: {}", value);
    }
}
