package com.saidigital.bookstore.bookservice.business.port.out.book;

import com.saidigital.bookstore.bookservice.domain.Book;

public interface FindBookByIdPort {

    Book findBookById(long bookId);

}
