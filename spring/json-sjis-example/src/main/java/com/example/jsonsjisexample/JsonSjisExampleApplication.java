package com.example.jsonsjisexample;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
        final Charset win31j = Charset.forName("Windows-31J");
        final ObjectMapper mapper = new ObjectMapper(new NonUtf8JsonFactory(win31j));

        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
        converter
            .setSupportedMediaTypes(Arrays.asList(new MediaType(MediaType.TEXT_PLAIN, win31j)));

        final RestTemplate rt = restTemplateBuilder
            .additionalMessageConverters(Arrays.asList(converter))
            .build();

        final Greeting greeging = rt.getForObject("http://localhost:8080/", Greeting.class);

        log.info("greeting text: {}", greeging.getText());
    }

    // https://github.com/FasterXML/jackson-core/issues/222
    @RequiredArgsConstructor
    private static class NonUtf8JsonFactory extends JsonFactory {
        private static final long serialVersionUID = 6370213897913075391L;

        @NonNull
        private final Charset charset;

        @Override
        public JsonParser createParser(final InputStream in) throws IOException, JsonParseException {
            return createParser(new InputStreamReader(in, charset));
        }
    }
}
