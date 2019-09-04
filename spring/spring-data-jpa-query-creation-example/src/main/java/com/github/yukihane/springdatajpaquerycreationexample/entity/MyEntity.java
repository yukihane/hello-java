package com.github.yukihane.springdatajpaquerycreationexample.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MyEntity {

    @Id
    private String myEntityId;

    private String altId;
}
