package com.example.filterautoregistrationexample.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import lombok.Getter;

@Getter
public class MyAuthentication extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 2912893466494653381L;

    private final MyCredentials credentials;
    private final MyPrincipal principal;

    public MyAuthentication(final MyCredentials credentials, final MyPrincipal principal) {
        super(null);
        setAuthenticated(true);
        this.credentials = credentials;
        this.principal = principal;
    }
}
