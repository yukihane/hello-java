package com.example.kotlin_form_binding

import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class MyController {

    @GetMapping("/")
    fun index(model: Model): String {
        return "index"
    }

    @GetMapping("/java")
    fun javaForm(model: Model, @ModelAttribute("profile") form: JavaForm): String {
        model.addAttribute("postUrl", "/java")
        return "form"
    }

    @PostMapping("/java")
    fun javaFormPost(
        model: Model,
        @Valid @ModelAttribute("profile") form: JavaForm,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes,
    ): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postUrl", "/java")
            return "form"
        }

        redirectAttributes.addFlashAttribute("message", "Javaフォームでのpostが成功しました")
        return "redirect:/"
    }

    @GetMapping("/dataClassVal")
    fun dataClassValForm(model: Model, @ModelAttribute("profile") form: DataClassValForm): String {
        model.addAttribute("postUrl", "/dataClassVal")
        return "form"
    }

    @PostMapping("/dataClassVal")
    fun dataClassValFormPost(
        model: Model,
        @Valid @ModelAttribute("profile") form: DataClassValForm,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes,
    ): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postUrl", "/java")
            return "form"
        }

        redirectAttributes.addFlashAttribute("message", "DataClassValFormでのpostが成功しました")
        return "redirect:/"
    }
}
