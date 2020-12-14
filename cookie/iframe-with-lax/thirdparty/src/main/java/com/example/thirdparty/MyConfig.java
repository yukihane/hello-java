package com.example.thirdparty;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@EnableWebSecurity
@EnableSpringHttpSession
@Configuration
public class MyConfig extends WebSecurityConfigurerAdapter {

    // 明示的にセッション管理用cookieにSameSiteを設定したいのでSpring Sessionを利用
    // https://docs.spring.io/spring-session/docs/current/reference/html5/#api-enablespringhttpsession
    @Bean
    public MapSessionRepository sessionRepository() {
        return new MapSessionRepository(new ConcurrentHashMap<>());
    }

    // https://docs.spring.io/spring-session/docs/current/reference/html5/#api-cookieserializer-bean
    @Bean
    public CookieSerializer cookieSerializer() {
        final DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setSameSite("Lax");
        return serializer;
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        // https://docs.spring.io/spring-security/site/docs/current/reference/html5/#servlet-headers-default
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
    }
}
