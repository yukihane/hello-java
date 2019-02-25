package com.github.yukihane.java.beanvalidationrest.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

    private String field;
    private String[] codes;
    private String objectName;

}
