package com.example.Book.Management.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
    private int publicationYear;
    private boolean isAvailable;
}