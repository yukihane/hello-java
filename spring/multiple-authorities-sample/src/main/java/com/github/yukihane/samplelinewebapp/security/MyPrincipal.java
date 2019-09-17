package com.github.yukihane.samplelinewebapp.security;

import java.io.Serializable;
import lombok.Data;
import org.springframework.security.core.AuthenticatedPrincipal;

@Data
public class MyPrincipal implements AuthenticatedPrincipal, Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
}
