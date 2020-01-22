package com.example.jsonsjisexample;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RestController
@RequestMapping("/")
@Slf4j
public class JsonSjisExampleApplication implements ApplicationRunner {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public static void main(final String[] args) {
        SpringApplication.run(JsonSjisExampleApplication.class, args);
    }

    // @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(produces = "text/plain;charset=Windows-31J")
    public String response() {
        return "{\"text\": \"こんにちは世界\" }";
    }

    @Data
    public static class Greeting {
        private String text;
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(new MediaType(MediaType.TEXT_PLAIN, Charset.forName("Windows-31J"))));

        final RestTemplate rt = restTemplateBuilder
            .additionalMessageConverters(Arrays.asList(converter))
            .build();

        final Greeting greeging = rt.getForObject("http://localhost:8080/", Greeting.class);

        log.info("greeting text: {}", greeging.getText());
    }

}
