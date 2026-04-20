package com.example.Book.Management.repository;

import com.example.Book.Management.document.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    // Custom query to find by ISBN if needed
    Book findByIsbn(String isbn);
}