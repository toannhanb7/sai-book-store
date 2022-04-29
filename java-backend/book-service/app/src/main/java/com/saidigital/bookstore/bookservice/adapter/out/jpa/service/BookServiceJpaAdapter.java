package com.saidigital.bookstore.bookservice.adapter.out.jpa.service;

import com.saidigital.bookstore.base.common.GetAllItem;
import com.saidigital.bookstore.bookservice.business.port.out.book.FindBookByIdPort;
import com.saidigital.bookstore.bookservice.domain.Author;
import com.saidigital.bookstore.bookservice.domain.Book;
import com.saidigital.bookstore.bookservice.adapter.out.jpa.entity.BookEntity;
import com.saidigital.bookstore.bookservice.adapter.out.jpa.repository.BookRepository;
import com.saidigital.bookstore.bookservice.business.port.out.book.FindAllBooksPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookServiceJpaAdapter implements FindAllBooksPort, FindBookByIdPort {

    private final BookRepository bookRepository;

    @Override
    public GetAllItem<Book> findAllBooks(String search, int page, int size, String sortBy, boolean sortDesc) {
        Specification<BookEntity> spec = findWithContains(search);
        List<Book> items = new ArrayList<>();
        long count = bookRepository.count(spec);
        if (count > 0) {
            items = bookRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sortDesc ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy)))
                    .map(this::convertEntityToDomain).stream().collect(Collectors.toList());
        }
        return new GetAllItem<>(count, items);
    }

    private Specification<BookEntity> findWithContains(String search) {
        return (root, query, builder) -> {
            return builder.like(root.get("name"), "%" + search + "%") ;
        };
    }

    @Override
    public Book findBookById(long bookId) {
        Optional<BookEntity> bookOpt = bookRepository.findById(bookId);
        return bookOpt.map(this::convertEntityToDomain).orElse(null);
    }

    private Book convertEntityToDomain(BookEntity bookEntity) {
        return new Book(
                bookEntity.getId(),
                bookEntity.getName(),
                new Author(
                        bookEntity.getAuthor().getId(),
                        bookEntity.getAuthor().getName()
                ),
                bookEntity.getDescription(),
                bookEntity.getPrice(),
                bookEntity.getImage()
        );
    }
}
