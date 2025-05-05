package com.example.record_spring_mvc_form;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MyController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("person", new Person());
        return "form";
    }

    @PostMapping("/form")
    public String submitForm(Model model, @Valid Person person, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        redirectAttributes.addFlashAttribute("message", "フォームの送信が成功しました！");
        return "redirect:/";
    }
}
