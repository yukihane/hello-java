package com.example.errorcontrollerthrowexceptionexample;

import java.util.Map;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/error")
public class MyErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping
    @ResponseBody
    public Map<String, String> error() {
        throw new RuntimeException();
    }
}
