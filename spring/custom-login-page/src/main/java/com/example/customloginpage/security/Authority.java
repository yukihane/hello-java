package com.example.customloginpage.security;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    HELLO, GOODBY;

    @Override
    public String getAuthority() {
        return toString();
    }

}
