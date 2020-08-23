package com.example.lombokmapstructexample;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @GetMapping
    public List<BookModel> get() {
        final List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::entityToModel).collect(Collectors.toList());
    }

    @PostMapping
    public BookModel post(@RequestBody final BookModel model) {
        final Book book = bookMapper.modelToEntity(model);
        final Book saved = bookRepository.save(book);

        return bookMapper.entityToModel(saved);
    }
}
