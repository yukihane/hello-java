package com.example.hexagonal_architecture

import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/users")
class UserController(
    private val personMapper: PersonMapper,
) {

    @GetMapping
    fun index(personForm: PersonForm, model: Model): String {
        val users = personMapper.findAll()
        model.addAttribute("users", users)
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
