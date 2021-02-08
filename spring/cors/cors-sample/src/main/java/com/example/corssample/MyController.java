package com.example.corssample;

import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MyController {

    @DeleteMapping("endpoint")
    public Map<String, String> delete() {
        return Map.of("result", "ok");
    }
}
