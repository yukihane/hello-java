package com.github.yukihane.java.beanvalidationrest.bean;

import com.github.yukihane.java.beanvalidationrest.validation.AlwaysNg;
import lombok.Data;

@Data
public class ValidErrorEntity {

    @AlwaysNg
    private String text;
}
