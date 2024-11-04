package com.example.spring_data_jpa_pagenation

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByName(name: String): List<User>
    fun findByNameContaining(name: String): List<User>
    fun findByNameContaining(name: String, pageable: Pageable): Page<User>
}
