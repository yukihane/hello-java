package com.example.openapisample;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.openapi.example.api.BooksApi;
import org.openapi.example.model.BookListModel;
import org.openapi.example.model.BookModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class BookController implements BooksApi {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public ResponseEntity<BookListModel> booksGet(final HttpServletRequest request, final UriComponentsBuilder builder,
        @Valid final Integer max) {

        final List<Book> books = bookRepository.findAll();

        Stream<Book> stream = books.stream();
        if (max != null) {
            stream = stream.limit(max.longValue());
        }
        final List<BookModel> res = stream
            .map(bookMapper::entityToModel)
            .collect(Collectors.toList());

        final BookListModel output = new BookListModel();
        output.books(res);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookModel> booksIdGet(final Long id) {
        final Optional<BookModel> book = bookRepository.findById(id.longValue())
            .map(bookMapper::entityToModel);

        return book.map(b -> new ResponseEntity<>(b, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<BookModel> booksPost(@Valid final BookModel bookModel) {
        final Book book = bookMapper.modelToEntity(bookModel);
        final Book saved = bookRepository.save(book);
        final BookModel savedModel = bookMapper.entityToModel(saved);
        return new ResponseEntity<>(savedModel, HttpStatus.OK);
    }

}
