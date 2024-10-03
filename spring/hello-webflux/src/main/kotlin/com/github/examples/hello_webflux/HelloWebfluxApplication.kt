package com.github.examples.hello_webflux

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class HelloWebfluxApplication

fun main(args: Array<String>) {
//	runApplication<HelloWebfluxApplication>(*args)

    val context = SpringApplication.run(HelloWebfluxApplication::class.java, *args)
    val grretingClient = context.getBean(GreetingClient::class.java)
    println(">> message = ${grretingClient.getMessage().block()}")
}
