package com.example.spring_thymeleaf_validation.business.entities.repositories

import org.springframework.stereotype.Repository
import com.example.spring_thymeleaf_validation.business.entities.Variety
import java.util.ArrayList
import java.util.LinkedHashMap

@Repository
class VarietyRepository {
    private val varietiesById: MutableMap<Int, Variety> = LinkedHashMap()
    
    init {
        val var1 = Variety(1,"Thymus vulgaris")
        varietiesById[var1.id] = var1
        
        val var2 = Variety(2,"Thymus x citriodorus")
        varietiesById[var2.id] = var2
        
        val var3 = Variety(3,"Thymus herba-barona")
        varietiesById[var3.id] = var3
        
        val var4 = Variety(4,"Thymus pseudolaginosus")
        varietiesById[var4.id] = var4
        
        val var5 = Variety(5,"Thymus serpyllum")
        varietiesById[var5.id] = var5
    }
    
    fun findAll(): List<Variety> {
        return ArrayList(varietiesById.values)
    }
    
    fun findById(id: Int): Variety? {
        return varietiesById[id]
    }
}
