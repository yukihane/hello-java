package com.example.springbootauthexample202006.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ApplicationUser {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public ApplicationUser(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
}
