package com.example.oauth2server.security;

import java.io.Serializable;
import lombok.Data;
import org.springframework.security.core.AuthenticatedPrincipal;

@Data
public class MyPrincipal implements AuthenticatedPrincipal, Serializable {

    private static final long serialVersionUID = -4162556580473630722L;

    private final String name;

}
