package com.example.bookmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import lombok.*;

@Document(collection = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private int publicationYear;


    // RELATIONSHIP: Link to the Librarian who added this book
    @DocumentReference(lazy = false)
    private User addedBy;

    // RELATIONSHIP: Link to the Student who currently borrowed it (can be null)
    @DocumentReference
    private User currentHolder;
}