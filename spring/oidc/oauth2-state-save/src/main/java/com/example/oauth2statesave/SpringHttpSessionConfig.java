package com.example.oauth2statesave;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

// https://docs.spring.io/spring-session/docs/current/reference/html5/#api-enablespringhttpsession
@EnableSpringHttpSession
@Configuration
public class SpringHttpSessionConfig {

    @Bean
    public MapSessionRepository sessionRepository() {
        return new MapSessionRepository(new ConcurrentHashMap<>());
    }
}
