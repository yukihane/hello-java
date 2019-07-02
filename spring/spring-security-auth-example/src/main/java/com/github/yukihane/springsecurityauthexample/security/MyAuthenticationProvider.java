package com.github.yukihane.springsecurityauthexample.security;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private DataSource dataSource;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        // この辺りは認証フィルタ(今回の場合Basic認証を指定しているので
        // BasicAuthenticationFilter)が生成している
        final UsernamePasswordAuthenticationToken upAuth = (UsernamePasswordAuthenticationToken) authentication;
        final String id = (String) authentication.getPrincipal();

        try {
            final String password = (String) upAuth.getCredentials();

            final String storedPassword = queryPassword(id);

            if (Objects.equals(password, "") || !Objects.equals(password, storedPassword)) {
                throw new BadCredentialsException("illegal id or passowrd");
            }
        } catch (final SQLException e) {
            throw new AuthenticationServiceException("db error", e);
        }

        final Object principal = authentication.getPrincipal();
        final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
            principal, authentication.getCredentials(),
            Collections.emptyList());
        result.setDetails(authentication.getDetails());

        return result;
    }

    private String queryPassword(final String id) throws SQLException {
        final PreparedStatement q = dataSource.getConnection()
            .prepareStatement("select password from user where id = ?");
        q.setString(1, id);
        final ResultSet rs = q.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return true;
    }

}
