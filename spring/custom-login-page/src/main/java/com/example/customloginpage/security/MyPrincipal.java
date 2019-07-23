package com.example.customloginpage.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.AuthenticatedPrincipal;

@Data
@AllArgsConstructor
public class MyPrincipal implements AuthenticatedPrincipal {
    private String name;
}
