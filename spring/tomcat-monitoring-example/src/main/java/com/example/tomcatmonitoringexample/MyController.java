package com.example.tomcatmonitoringexample;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping
    Map<String, String> index() {
        return Map.of("result", "ok");
    }
}
