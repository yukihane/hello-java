package com.github.yukihane.java.beanvalidationrest.controller.response;

import java.util.List;
import lombok.Data;

@Data
public class ValidationResponse {

    private List<ConstraintViolationResponse> errors;
}
