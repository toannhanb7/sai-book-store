package com.saidigital.bookstore.bookservice.business.port.in.book;

import com.saidigital.bookstore.base.common.GetAllItem;
import com.saidigital.bookstore.bookservice.domain.Book;

public interface GetAllBooksUseCase {

    GetAllItem<Book> getAllBooks(GetAllBooksCommand command);

}
