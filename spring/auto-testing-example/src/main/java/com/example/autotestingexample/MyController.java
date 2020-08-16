package com.example.autotestingexample;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;
    private final MyMapper myMapper;

    @GetMapping("/")
    public String hello(final Model model) {

        model.addAttribute("now", myService.getNow());
        model.addAttribute("boot", myMapper.select());
        return "index";
    }
}
