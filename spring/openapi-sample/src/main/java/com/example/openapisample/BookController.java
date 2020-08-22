package com.example.openapisample;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.openapi.example.api.BooksApi;
import org.openapi.example.model.BookListModel;
import org.openapi.example.model.BookModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController implements BooksApi {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public ResponseEntity<BookListModel> booksGet() {
        final List<Book> books = bookRepository.findAll();
        final List<BookModel> res = books.stream().map(bookMapper::entityToModel).collect(Collectors.toList());
        final BookListModel output = new BookListModel();
        output.books(res);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookModel> booksPost(@Valid final BookModel bookModel) {
        final Book book = bookMapper.modelToEntity(bookModel);
        final Book saved = bookRepository.save(book);
        final BookModel savedModel = bookMapper.entityToModel(saved);
        return new ResponseEntity<>(savedModel, HttpStatus.OK);
    }
}
