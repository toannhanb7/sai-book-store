package com.saidigital.bookstore.bookservice.business.port.out.book;

import com.saidigital.bookstore.base.common.GetAllItem;
import com.saidigital.bookstore.bookservice.domain.Book;

public interface FindAllBooksPort {

    GetAllItem<Book> findAllBooks(String search, int page, int size, String sortBy, boolean sortDesc);

}
