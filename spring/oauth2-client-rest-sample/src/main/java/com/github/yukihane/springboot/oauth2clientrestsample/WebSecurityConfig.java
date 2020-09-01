package com.github.yukihane.springboot.oauth2clientrestsample;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // POSTできるように一旦解除しておく
        http.csrf().disable();

        // https://yukihane.github.io/blog/202007/21/hello-oidc-with-keycloak/
        // OAuth2WebSecurityConfiguration 設定をそのままコピー
        http.authorizeRequests((requests) -> requests.antMatchers("/api/**").authenticated());
        http.oauth2Login(Customizer.withDefaults());
        http.oauth2Client();

    }
}
