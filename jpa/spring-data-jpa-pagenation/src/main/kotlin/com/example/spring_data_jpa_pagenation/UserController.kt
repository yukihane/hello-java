package com.example.spring_data_jpa_pagenation

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class UserController(
    private val userRepository: UserRepository,
) {
    @GetMapping("/users")
    fun users(
        @RequestParam(required = false) name: String?,
        model: Model,
        pageable: Pageable,
    ): String {
        val page = if (name == null) {
            Page.empty()
        } else {
            userRepository.findByNameContaining(name, pageable)
        }

        val users = page.getContent()
        model.addAttribute(page)
        model.addAttribute(users)
        return "user-list"
    }
}
