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
            model.addAttribute("postUrl", "/dataClassVal")
            return "form"
        }

        redirectAttributes.addFlashAttribute("message", "DataClassValFormでのpostが成功しました")
        return "redirect:/"
    }


    @GetMapping("/dataClassValNullable")
    fun dataClassValNullableForm(model: Model, @ModelAttribute("profile") form: DataClassValNullableForm): String {
        model.addAttribute("postUrl", "/dataClassValNullable")
        return "form"
    }

    @PostMapping("/dataClassValNullable")
    fun dataClassValFormPostNullable(
        model: Model,
        @Valid @ModelAttribute("profile") form: DataClassValNullableForm,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes,
    ): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postUrl", "/dataClassValNullable")
            return "form"
        }

        redirectAttributes.addFlashAttribute("message", "DataClassValNullableForm でのpostが成功しました")
        return "redirect:/"
    }


    @GetMapping("/dataClassVar")
    fun dataClassVarForm(model: Model, @ModelAttribute("profile") form: DataClassVarForm): String {
        model.addAttribute("postUrl", "/dataClassValNullable")
        return "form"
    }

    @PostMapping("/dataClassVar")
    fun dataClassVarFormPost(
        model: Model,
        @Valid @ModelAttribute("profile") form: DataClassVarForm,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes,
    ): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postUrl", "/dataClassVar")
            return "form"
        }

        redirectAttributes.addFlashAttribute("message", "DataClassVarForm でのpostが成功しました")
        return "redirect:/"
    }

    @GetMapping("/kotlin")
    fun kotlinForm(model: Model, @ModelAttribute("profile") form: KotlinForm): String {
        model.addAttribute("postUrl", "/kotlin")
        return "form"
    }

    @PostMapping("/kotlin")
    fun kotlinFormPost(
        model: Model,
        @Valid @ModelAttribute("profile") form: KotlinForm,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes,
    ): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postUrl", "/kotlin")
            return "form"
        }

        redirectAttributes.addFlashAttribute("message", "KotlinForm でのpostが成功しました")
        return "redirect:/"
    }
}
