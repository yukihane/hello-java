package com.example.thymeleaf_example;

import com.baeldung.thymeleaf.utils.StudentUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// https://www.baeldung.com/spring-thymeleaf-fragments
@Controller
public class FragmentsController {

    @GetMapping("/fragments")
    public String getHome() {
        return "fragments";
    }

    @GetMapping("/markup")
    public String markupPage() {
        return "markup";
    }

    @GetMapping("/params")
    public String paramsPage() {
        return "params";
    }

    @GetMapping("/other")
    public String otherPage(Model model) {
        model.addAttribute("data", StudentUtils.buildStudents());
        return "other";
    }
}
