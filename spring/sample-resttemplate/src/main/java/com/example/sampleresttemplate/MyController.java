package com.example.sampleresttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
@Slf4j
public class MyController {

    private final RestTemplate restTemplate;

    public MyController(final RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    @GetMapping
    public String github() {
        final ClientHttpRequestFactory rf = restTemplate.getRequestFactory();
        log.info("Using RequestFactory: {}", rf.getClass().getName());

        final ResponseEntity<String> res = restTemplate.getForEntity("https://api.github.com/users/yukihane/repos",
            String.class);
        return res.getBody();
    }
}
