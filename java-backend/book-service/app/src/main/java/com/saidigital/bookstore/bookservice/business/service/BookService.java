package com.saidigital.bookstore.bookservice.business.service;

import com.saidigital.bookstore.bookservice.business.port.out.book.FindBookByIdPort;
import com.saidigital.bookstore.base.common.GetAllItem;
import com.saidigital.bookstore.base.exception.UserException;
import com.saidigital.bookstore.bookservice.business.port.in.book.GetAllBooksCommand;
import com.saidigital.bookstore.bookservice.business.port.in.book.GetAllBooksUseCase;
import com.saidigital.bookstore.bookservice.business.port.in.book.GetBookByIdCommand;
import com.saidigital.bookstore.bookservice.business.port.in.book.GetBookByIdUseCase;
import com.saidigital.bookstore.bookservice.business.port.out.book.FindAllBooksPort;
import com.saidigital.bookstore.bookservice.domain.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookService implements GetBookByIdUseCase, GetAllBooksUseCase {

    private final FindAllBooksPort findAllBooksPort;

    private final FindBookByIdPort findBookByIdPort;

    @Override
    public GetAllItem<Book> getAllBooks(GetAllBooksCommand command) {
        return findAllBooksPort.findAllBooks(command.getSearch(), command.getPage(), command.getSize(), command.getSortBy(), command.isSortDesc());
    }

    @Override
    public Book getBookById(GetBookByIdCommand command) {
        log.debug("Get Book with id {}", command.getBookId());
        Book book = findBookByIdPort.findBookById(command.getBookId());
        if (book == null) {
            throw new UserException("book_not_found");
        }
        return book;
    }
}
