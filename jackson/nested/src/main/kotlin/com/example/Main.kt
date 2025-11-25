package com.example

import com.example.models.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

fun main() {
    val mapper = jacksonObjectMapper()

    val testCases = listOf(
        // TypeAChild1: skip = true
        """{"name": "test-a1", "type": "type-a", "skip": true}""",
        // TypeAChild2: skip = false, additional required
        """{"name": "test-a2", "type": "type-a", "skip": false, "additional": "extra-a"}""",
        // TypeBChild1: skip = true
        """{"name": "test-b1", "type": "type-b", "skip": true}""",
        // TypeBChild2: skip = false, additional required
        """{"name": "test-b2", "type": "type-b", "skip": false, "additional": "extra-b"}""",
        // TypeC: additional required
        """{"name": "test-c", "type": "type-c", "additional": "extra-c"}"""
    )

    println("=== Nested JSON Mapping Test ===\n")

    testCases.forEach { json ->
        println("Input JSON: $json")
        val result: Root = mapper.readValue(json)
        println("Parsed type: ${result::class.simpleName}")
        println("Result: $result")
        println()
    }

    println("=== All tests passed! ===")
}
