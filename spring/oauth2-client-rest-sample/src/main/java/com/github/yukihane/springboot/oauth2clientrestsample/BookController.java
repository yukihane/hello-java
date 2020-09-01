package com.github.yukihane.springboot.oauth2clientrestsample;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("")
    public List<Book> list() {
        return bookRepository.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody final Book book) {
        bookRepository.save(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
