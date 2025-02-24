package com.example.hexagonal_architecture

import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/users")
class UserController {

    @GetMapping
    fun index(personForm: PersonForm): String {
        return "users/index"
    }

    @PostMapping
    fun register(@Valid personForm: PersonForm, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "users/index"
        }
        return "redirect:/users/result"
    }

    @GetMapping("/result")
    fun result(): String {
        return "users/result"
    }
}
