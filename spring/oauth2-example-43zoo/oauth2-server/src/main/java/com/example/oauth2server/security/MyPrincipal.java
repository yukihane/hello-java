package com.example.oauth2server.security;

import lombok.Data;
import org.springframework.security.core.AuthenticatedPrincipal;

@Data
public class MyPrincipal implements AuthenticatedPrincipal {

    private final String name;

}
