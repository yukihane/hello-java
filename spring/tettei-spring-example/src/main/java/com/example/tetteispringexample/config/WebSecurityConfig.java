package com.example.tetteispringexample.config;

import com.example.tetteispringexample.security.UserAuthentication;
import com.example.tetteispringexample.user.domain.User;
import com.example.tetteispringexample.user.repository.UserRepository;
import java.util.Arrays;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/js/**", "/css/**").permitAll()
            .antMatchers("/**").authenticated()
            .and()
            .formLogin().loginPage("/loginForm")
            .loginProcessingUrl("/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/rooms", true)
            .failureUrl(
                "/loginForm?error=true")
            .permitAll();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new ProviderManager(Arrays.asList(new MyAuthenticationProvider(passwordEncoder, userRepository)));
    }

    @RequiredArgsConstructor
    public static class MyAuthenticationProvider implements AuthenticationProvider {

        private final PasswordEncoder passwordEncoder;
        private final UserRepository userRepository;

        @Override
        public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
            final UsernamePasswordAuthenticationToken authn = (UsernamePasswordAuthenticationToken) authentication;
            final String username = (String) authn.getPrincipal();
            final String password = (String) authn.getCredentials();

            final Optional<User> user = userRepository.findById(username);

            final Optional<UserAuthentication> result = user.map(u -> {
                if (passwordEncoder.matches(password, u.getPassword())) {
                    return new UserAuthentication(username);
                } else {
                    return null;
                }
            });

            return result.orElseThrow(() -> new BadCredentialsException("illegal username or password"));
        }

        @Override
        public boolean supports(final Class<?> authentication) {
            return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
        }
    }
}
