package com.example.springbootauthexample202006.security;

import java.util.Collection;
import java.util.List;
import lombok.Data;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@Data
public class ApplicationUserAuthentication implements Authentication {
    private static final long serialVersionUID = 1L;

    @Data
    public static class Principal implements AuthenticatedPrincipal {
        private final String username;

        @Override
        public String getName() {
            return username;
        }
    }

    private final Collection<? extends GrantedAuthority> authorities = List.of();
    private final Object details = null;
    private final Object credentials = null;
    private final Principal principal;
    private boolean authenticated;

    public ApplicationUserAuthentication(final String username) {
        this.principal = new Principal(username);
        this.authenticated = true;
    }

    @Override
    public String getName() {
        return principal.getUsername();
    }
}
