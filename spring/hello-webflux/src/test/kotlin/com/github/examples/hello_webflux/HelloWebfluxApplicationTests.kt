package com.github.examples.hello_webflux

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloWebfluxApplicationTests {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun testHello() {
        webTestClient // Create a GET request to test an endpoint
            .get().uri("/hello")
            .accept(MediaType.APPLICATION_JSON)
            .exchange() // and use the dedicated DSL to test assertions against the response
            .expectStatus().isOk()
            .expectBody<Greeting>(Greeting::class.java).value {
                assertThat(it.message).isEqualTo("Hello, Spring!")
            }
    }
}
