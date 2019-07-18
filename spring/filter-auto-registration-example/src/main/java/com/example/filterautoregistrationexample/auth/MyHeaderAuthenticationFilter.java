package com.example.filterautoregistrationexample.auth;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * リクエストヘッダに{@code x-auth-name}が設定されていたら認証OKとする(値を名前とする)フィルタ。
 *
 * @see <a href=
 * "https://docs.spring.io/spring-security/site/docs/5.1.5.RELEASE/reference/html/advanced-topics.html#preauth">
 * 12.2 Pre-Authentication Scenarios</a>
 */
@Component
public class MyHeaderAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    private static final String AUTH_HEADER = "x-auth-name";

    public MyHeaderAuthenticationFilter() {
        setCheckForPrincipalChanges(true);
        setContinueFilterChainOnUnsuccessfulAuthentication(false);
        setAuthenticationManager(new ProviderManager(Arrays.asList(new NoOpAuthenticationProvider())));
    }

    @Override
    protected MyPrincipal getPreAuthenticatedPrincipal(final HttpServletRequest request) {
        final String name = request.getHeader(AUTH_HEADER);
        return new MyPrincipal(name);
    }

    @Override
    protected MyCredentials getPreAuthenticatedCredentials(final HttpServletRequest request) {
        return new MyCredentials();
    }

    private static class NoOpAuthenticationProvider implements AuthenticationProvider {

        @Override
        public final boolean supports(final Class<?> authentication) {
            return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
        }

        @Override
        public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
            final MyCredentials credentials = (MyCredentials) authentication.getCredentials();
            final MyPrincipal principal = (MyPrincipal) authentication.getPrincipal();
            final MyAuthentication ret = new MyAuthentication(credentials, principal);
            if (StringUtils.isEmpty(principal.getName())) {
                throw new MyAuthenticationException("Failed: MyHeaderAuthenticationFilter");
            }
            return ret;
        }
    }

    public static class MyAuthenticationException extends AuthenticationException {

        private static final long serialVersionUID = 6215305345480845727L;

        public MyAuthenticationException(final String msg) {
            super(msg);
        }
    }
}
