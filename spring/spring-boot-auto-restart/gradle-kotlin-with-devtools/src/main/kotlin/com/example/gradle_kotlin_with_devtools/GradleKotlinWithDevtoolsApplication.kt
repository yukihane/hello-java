package com.example.gradle_kotlin_with_devtools

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GradleKotlinWithDevtoolsApplication

fun main(args: Array<String>) {
	runApplication<GradleKotlinWithDevtoolsApplication>(*args)
}
