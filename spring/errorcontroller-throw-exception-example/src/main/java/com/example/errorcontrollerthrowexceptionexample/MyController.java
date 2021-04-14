package com.example.errorcontrollerthrowexceptionexample;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class MyController {

    @GetMapping("")
    Map<String, String> index() {
        throw new RuntimeException();
    }
}
