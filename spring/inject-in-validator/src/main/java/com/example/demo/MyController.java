package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @PostMapping("/")
    public MyEntity index(@RequestBody final MyRequest req) {
        final MyEntity resp = new MyEntity();
        resp.setName(req.name());
        return resp;
    }

    //    @Data
    //    public static class MyRequest {
    //        private String name;
    //        private int age;
    //    }

    public static record MyRequest(String name, String age) {
    }
}
