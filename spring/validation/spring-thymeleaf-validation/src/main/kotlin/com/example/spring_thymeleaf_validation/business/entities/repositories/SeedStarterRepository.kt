package com.example.spring_thymeleaf_validation.business.entities.repositories

import org.springframework.stereotype.Repository
import com.example.spring_thymeleaf_validation.business.entities.SeedStarter
import java.util.ArrayList

@Repository
class SeedStarterRepository {
    private val seedStarters: MutableList<SeedStarter> = ArrayList()
    
    fun findAll(): List<SeedStarter> {
        return ArrayList(seedStarters)
    }
    
    fun add(seedStarter: SeedStarter) {
        seedStarters.add(seedStarter)
    }
}
