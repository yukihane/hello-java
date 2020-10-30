package com.example.loggingexample;

import static net.logstash.logback.argument.StructuredArguments.v;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@Slf4j
public class MyController {

    @GetMapping
    public String index() {
        log.info("This is info message");
        log.info("JSON Log", v("Hello", "Japan"), v("Hello", "World"));
        return "{}";
    }

    @GetMapping("/exception")
    public String exeption() throws Exception {
        throw new Exception("This is sample exception message!");
    }

    @GetMapping("/myexception")
    public String myexeption() {
        throw new MyException("This is sample exception message!");
    }
}
