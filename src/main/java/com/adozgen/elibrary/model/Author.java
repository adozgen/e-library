package com.adozgen.elibrary.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "zipcode_id")
    private Zipcode zipcode;
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();
    public Author(String name, Zipcode zipcode, List<Book> books) {
        this.name = name;
        this.zipcode = zipcode;
        this.books = books;
    }

    public void _addBook(Book book) {
        books.add(book);
    }

    public void _removeBook(Book book) {
        books.remove(book);
    }

}
