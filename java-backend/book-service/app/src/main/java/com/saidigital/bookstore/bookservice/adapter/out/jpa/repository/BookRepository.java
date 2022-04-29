package com.saidigital.bookstore.bookservice.adapter.out.jpa.repository;

import com.saidigital.bookstore.bookservice.adapter.out.jpa.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {
}
