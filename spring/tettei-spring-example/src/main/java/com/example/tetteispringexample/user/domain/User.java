package com.example.tetteispringexample.user.domain;

import lombok.Data;

@Data
public class User {

    private String userId;
    private String password;
    private String firstName;
    private String lastName;

    private RoleName roleName;

}
