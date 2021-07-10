package com.github.yukihane.withts;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MyController {

    @GetMapping
    public String index(final Model model) {
        model.addAttribute("now", new Date());
        return "index";
    }
}
