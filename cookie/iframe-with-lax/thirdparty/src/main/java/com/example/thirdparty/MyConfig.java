package com.example.thirdparty;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

@EnableWebSecurity
@EnableSpringHttpSession
@Configuration
public class MyConfig extends WebSecurityConfigurerAdapter {

    // 明示的にセッション管理用cookieにSameSiteを設定したいのでSpring Sessionを利用
    // https://docs.spring.io/spring-session/docs/current/reference/html5/#api-enablespringhttpsession
    // https://docs.spring.io/spring-session/docs/current/reference/html5/#api-cookieserializer-customization
    @Bean
    public MapSessionRepository sessionRepository() {
        return new MapSessionRepository(new ConcurrentHashMap<>());
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        // https://docs.spring.io/spring-security/site/docs/current/reference/html5/#servlet-headers-default
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
    }
}
