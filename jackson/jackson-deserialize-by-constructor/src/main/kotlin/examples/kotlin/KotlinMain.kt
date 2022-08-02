package examples.kotlin

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import examples.java.JavaMain.JSON_TEXT

fun main() {
    val om = jacksonObjectMapper()
    om.registerModule(kotlinModule())

    val person = om.readValue<Person>(JSON_TEXT)
    println(person)
}

data class Person(val name: String, val age: Int)
