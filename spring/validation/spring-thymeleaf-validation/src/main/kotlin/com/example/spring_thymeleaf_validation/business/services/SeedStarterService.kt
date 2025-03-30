package com.example.spring_thymeleaf_validation.business.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.example.spring_thymeleaf_validation.business.entities.SeedStarter
import com.example.spring_thymeleaf_validation.business.entities.repositories.SeedStarterRepository

@Service
class SeedStarterService (
    private val seedstarterRepository: SeedStarterRepository,
) {
    fun findAll(): List<SeedStarter> {
        return seedstarterRepository.findAll()
    }
    
    fun add(seedStarter: SeedStarter) {
        seedstarterRepository.add(seedStarter)
    }
}
