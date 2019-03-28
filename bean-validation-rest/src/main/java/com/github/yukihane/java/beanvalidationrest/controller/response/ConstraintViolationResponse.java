package com.github.yukihane.java.beanvalidationrest.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConstraintViolationResponse {

    private String field;
    private String[] codes;
    private String objectName;
    private String message;

}
