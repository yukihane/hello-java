package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MyController {


    @GetMapping("/")
    public String index(final Model model, final HttpServletRequest req) {
        final String number = (String) req.getSession().getAttribute("number");
        final MyData data = new MyData();
        data.setNumber(number);
        model.addAttribute("data", data);
        return "index";
    }

    @PostMapping("/number")
    public String number(@ModelAttribute final MyData data, final HttpServletRequest req) {
        req.getSession().setAttribute("number", data.getNumber());
        return "redirect:/";
    }

    @GetMapping("/secure")
    public String secure(final Model model, final HttpServletRequest req) {
        final String number = (String) req.getSession().getAttribute("number");
        model.addAttribute("number", number);
        return "secure";
    }
}
