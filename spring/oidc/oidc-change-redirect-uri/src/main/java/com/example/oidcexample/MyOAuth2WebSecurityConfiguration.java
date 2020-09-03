package com.example.oidcexample;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// OAuth2WebSecurityConfigurerAdapter をコピー
@EnableWebSecurity
public class MyOAuth2WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
        http.oauth2Login()
            .loginProcessingUrl("/my_redirect_uri"); // この部分を追加
        http.oauth2Client();
    }
}
