package com.example.customloginpage.security;

import java.util.Collection;
import java.util.Collections;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@Data
@RequiredArgsConstructor
public class MyAuthentication implements Authentication {

    private static final long serialVersionUID = -5475798609765978684L;

    private final MyPrincipal principal;

    private final MyCredentials credentials;

    private boolean authenticated = true;

    @Override
    public String getName() {
        return principal.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public Object getDetails() {
        return null;
    }
}
