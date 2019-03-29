package com.github.yukihane.java.beanvalidationrest.validation;

import java.util.List;
import javax.validation.Payload;
import lombok.Data;

@Data
public class AdditionalData implements Payload {

    List<String> fields;
}
