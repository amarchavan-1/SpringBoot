package com.example.bookmanagement.service;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // YOUR SPECIAL REQUIREMENT: Null-Safe / Partial Update Logic
    public Book updateBook(String id, Book incomingData) {
        Book existingBook = getBookById(id);

        // Update title only if new title is not null or empty
        if (incomingData.getTitle() != null && !incomingData.getTitle().trim().isEmpty()) {
            existingBook.setTitle(incomingData.getTitle());
        }

        // Update author only if not null or empty
        if (incomingData.getAuthor() != null && !incomingData.getAuthor().trim().isEmpty()) {
            existingBook.setAuthor(incomingData.getAuthor());
        }

        // Update ISBN only if not null or empty
        if (incomingData.getIsbn() != null && !incomingData.getIsbn().trim().isEmpty()) {
            existingBook.setIsbn(incomingData.getIsbn());
        }

        // Update Year only if it's a valid year (not default 0)
        if (incomingData.getPublicationYear() > 0) {
            existingBook.setPublicationYear(incomingData.getPublicationYear());
        }

        // Update Availability and Relationship
        existingBook.setAvailable(incomingData.isAvailable());
        if (incomingData.getCurrentHolder() != null) {
            existingBook.setCurrentHolder(incomingData.getCurrentHolder());
        }

        return bookRepository.save(existingBook);
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
}