package com.example.RESTAPI.service;

import com.example.RESTAPI.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void save(Book book);       // Save a book
    List<Book> findAll();       // Get all books
    Optional<Book> findById(int id); // Find book by id
    boolean delete(int id);     // Delete book
}

