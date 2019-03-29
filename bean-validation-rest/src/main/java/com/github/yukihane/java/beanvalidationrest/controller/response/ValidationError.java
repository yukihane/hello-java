package com.github.yukihane.java.beanvalidationrest.controller.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {

    private List<String> paths;

    private String code;

    private String message;
}
