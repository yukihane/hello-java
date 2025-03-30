package com.example.spring_thymeleaf_validation.business.entities

enum class Feature(val value: String) {
    
    SEEDSTARTER_SPECIFIC_SUBSTRATE("SEEDSTARTER_SPECIFIC_SUBSTRATE"), 
    FERTILIZER("FERTILIZER"), 
    PH_CORRECTOR("PH_CORRECTOR");

    companion object {
        val ALL = arrayOf(SEEDSTARTER_SPECIFIC_SUBSTRATE, FERTILIZER, PH_CORRECTOR)
        
        fun forName(name: String): Feature {
            return when (name.uppercase()) {
                "SEEDSTARTER_SPECIFIC_SUBSTRATE" -> SEEDSTARTER_SPECIFIC_SUBSTRATE
                "FERTILIZER" -> FERTILIZER
                "PH_CORRECTOR" -> PH_CORRECTOR
                else -> throw IllegalArgumentException("Name \"$name\" does not correspond to any Feature")
            }
        }
    }

    override fun toString(): String {
        return value
    }
}
