package com.example.springboot.property.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.springboot.bean.PrototypeScopeBean;
import com.example.springboot.bean.PrototypeScopeProxyBean;
import com.example.springboot.bean.SingletonScopeBean;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link Service} とは、{@link Component}のうち、(DDDで言うところの)サービスを表すステレオタイプ。
 * <p>
 * デフォルト設定では、log levelはINFO、 出力先は標準出力。ログ関連の設定方法については4.1節を参照してください。
 *
 * @see <a href=
 * "https://docs.spring.io/spring-boot/docs/2.2.0.M1/reference/html/spring-boot-features.html#boot-features-logging-format">Spring
 * Boot Features - 4.1.
 * Log Format</a>
 */
@Slf4j
@Service
public class MyServiceImpl implements MyService {

    @Autowired
    private PrototypeScopeProxyBean proxyBean;

    @Autowired
    private PrototypeScopeBean prototypeBean;

    @Autowired
    private SingletonScopeBean singletonBean;

    @Override
    public String getText() {
        // デフォルトではDEBUGレベルはログ出力されない。
        log.debug("proxy: {}", proxyBean.getClass().getName());

        return String.format("prototype-proxy: %d, prototype: %d, singleton: %d",
            proxyBean.getValue(), prototypeBean.getValue(), singletonBean.getValue());
    }

}
