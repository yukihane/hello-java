package com.example.errorcontrollerthrowexceptionexample;

import org.apache.catalina.Container;
import org.apache.catalina.core.StandardHost;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    // https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#howto-use-tomcat-legacycookieprocessor
    // https://github.com/spring-projects/spring-boot/issues/21257#issuecomment-745565376
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> errorReportValveCustomizer() {

        return (factory) -> {
            factory.addContextCustomizers(context -> {
                final Container parent = context.getParent();
                if (parent instanceof StandardHost) {
                    ((StandardHost) parent).setErrorReportValveClass(
                        "com.example.errorcontrollerthrowexceptionexample.CustomErrorReportValve");
                }
            });
        };
    }
}
