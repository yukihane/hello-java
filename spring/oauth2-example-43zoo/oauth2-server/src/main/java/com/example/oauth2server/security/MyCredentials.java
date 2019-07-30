package com.example.oauth2server.security;

import java.io.Serializable;
import lombok.Data;

@Data
public class MyCredentials implements Serializable {

    private static final long serialVersionUID = -4658330939385972601L;

    private final String name;
}
