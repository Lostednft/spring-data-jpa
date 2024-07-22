package com.bookstore.jpa_youtube.controllers;

import com.bookstore.jpa_youtube.dtos.BookModelDTO;
import com.bookstore.jpa_youtube.models.BookModel;
import com.bookstore.jpa_youtube.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookstores/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    private ResponseEntity<BookModel> saveBook (@RequestBody BookModelDTO bookModelDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookModelDTO));
    }

    @GetMapping
    private ResponseEntity<List<BookModel>> allBooks()
    {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    private ResponseEntity<BookModel> bookByPublisherId(@PathVariable("id") UUID publisherId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookByPublisherId(publisherId));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteBookById(@PathVariable("id") UUID id){
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully.");
    }
}
