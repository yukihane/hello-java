package com.example.openapisample;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openapi.example.api.BooksApi;
import org.openapi.example.model.BookListModel;
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
        books.stream().map(bookMapper::entityToModel);
        final BookListModel output = new BookListModel();
        output.books(List.of());
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
