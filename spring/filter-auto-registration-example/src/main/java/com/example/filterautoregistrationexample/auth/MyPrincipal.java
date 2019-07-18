package com.example.filterautoregistrationexample.auth;

import org.springframework.security.core.AuthenticatedPrincipal;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MyPrincipal implements AuthenticatedPrincipal {

    private final String name;
}
