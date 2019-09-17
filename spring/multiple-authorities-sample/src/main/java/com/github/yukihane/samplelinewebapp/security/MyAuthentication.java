package com.github.yukihane.samplelinewebapp.security;

import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

@Data
public class MyAuthentication implements Authentication {

    private static final long serialVersionUID = 1L;

    private final MyCredentials credentials;
    private final MyPrincipal principal;

    @Override
    public String getName() {
        return principal.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("basicauth");
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

}
