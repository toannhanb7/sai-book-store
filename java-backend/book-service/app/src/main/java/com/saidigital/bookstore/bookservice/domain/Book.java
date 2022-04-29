package com.saidigital.bookstore.bookservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id;

    private String name;

    private Author author;

    private String description;

    private double price;

    private String image;
}
