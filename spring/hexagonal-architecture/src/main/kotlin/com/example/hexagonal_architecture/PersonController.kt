package com.example.hexagonal_architecture

import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/people")
class PersonController(
    private val personMapper: PersonMapper,
    private val personConverter: PersonConverter,
) {

    @GetMapping
    fun index(personForm: PersonForm, model: Model): String {
        val people = personMapper.findAll()
        model.addAttribute("people", people)
        return "people/index"
    }

    @PostMapping
    fun register(@Valid personForm: PersonForm, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "people/index"
        }

        val entity = personConverter.toEntity(personForm)
        personMapper.insert(entity)
        return "redirect:/people/result"
    }

    @GetMapping("/result")
    fun result(): String {
        return "people/result"
    }
}
