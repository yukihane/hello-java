package com.github.yukihane.record_spring_mvc;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyHtmlController {

    @PostMapping
    public String index(@ModelAttribute final FormData form, final Model model) {
        model.addAttribute("form", form);
        return "index";
    }

    public record FormData(
        String name,
        int age,
        @DateTimeFormat(pattern = "uuuu-MM-dd") LocalDate registrationDate) {
    }
}
