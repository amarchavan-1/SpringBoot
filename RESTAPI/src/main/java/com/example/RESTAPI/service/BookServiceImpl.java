package com.example.RESTAPI.service;
import com.example.RESTAPI.model.Book;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private final Map<Integer, Book> bookMap = new HashMap<>();

    @Override
    public void save(Book book) {
        bookMap.put(Math.toIntExact(book.getId()), book);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public Optional<Book> findById(int id) {
        return Optional.ofNullable(bookMap.get(id));
    }

    @Override
    public boolean delete(int id) {
        return bookMap.remove(id) != null;
    }
}
