package com.saidigital.bookstore.bookservice.adapter.in.restapi.v1.api.delegate;

import com.saidigital.bookstore.bookservice.adapter.in.restapi.v1.api.BookApiDelegate;
import com.saidigital.bookstore.bookservice.adapter.in.restapi.v1.model.Book;
import com.saidigital.bookstore.bookservice.adapter.in.restapi.v1.model.BookGetAll;
import com.saidigital.bookstore.bookservice.business.port.in.book.GetAllBooksCommand;
import com.saidigital.bookstore.bookservice.business.port.in.book.GetAllBooksUseCase;
import com.saidigital.bookstore.bookservice.business.port.in.book.GetBookByIdCommand;
import com.saidigital.bookstore.bookservice.business.port.in.book.GetBookByIdUseCase;
import com.saidigital.bookstore.base.common.GetAllItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Log4j2
@RequiredArgsConstructor
public class BookApiDelegateImpl implements BookApiDelegate {

    private final GetBookByIdUseCase getBookByIdUseCase;

    private final GetAllBooksUseCase getAllBooksUseCase;

    @Override
    public ResponseEntity<Book> getBookById(Long id) {
        com.saidigital.bookstore.bookservice.domain.Book book = getBookByIdUseCase.getBookById(new GetBookByIdCommand(id));
        return ResponseEntity.ok(convertDomainToRestEntity(book));
    }

    private Book convertDomainToRestEntity(com.saidigital.bookstore.bookservice.domain.Book book) {
        return new Book()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor().getName())
                .description(book.getDescription())
                .image(book.getImage())
                // assume we only support USD now
                .price(book.getPrice())
                .currency("USD")
                ;
    }

    @Override
    public ResponseEntity<BookGetAll> getAllBooks(String search, Integer page, Integer size, String sortBy, Boolean sortDesc) {
        GetAllItem<com.saidigital.bookstore.bookservice.domain.Book> booksGetAll = getAllBooksUseCase.getAllBooks(new GetAllBooksCommand(search, page, size, sortBy, sortDesc));
        return ResponseEntity.ok(new BookGetAll()
                .total(booksGetAll.getTotal())
                .items(booksGetAll.getItems().stream().map(this::convertDomainToRestEntity).collect(Collectors.toList()))
        );
    }
}
