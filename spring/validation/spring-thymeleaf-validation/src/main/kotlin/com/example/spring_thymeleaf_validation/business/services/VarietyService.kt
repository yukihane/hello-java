package com.example.spring_thymeleaf_validation.business.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.example.spring_thymeleaf_validation.business.entities.Variety
import com.example.spring_thymeleaf_validation.business.entities.repositories.VarietyRepository

@Service
class VarietyService (
    private val varietyRepository: VarietyRepository,
) {
    fun findAll(): List<Variety> {
        return varietyRepository.findAll()
    }
    
    fun findById(id: Int): Variety? {
        return varietyRepository.findById(id)
    }
}
