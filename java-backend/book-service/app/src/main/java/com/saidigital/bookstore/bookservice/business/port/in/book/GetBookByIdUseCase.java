package com.saidigital.bookstore.bookservice.business.port.in.book;

import com.saidigital.bookstore.bookservice.domain.Book;

public interface GetBookByIdUseCase {

    Book getBookById(GetBookByIdCommand command);

}
