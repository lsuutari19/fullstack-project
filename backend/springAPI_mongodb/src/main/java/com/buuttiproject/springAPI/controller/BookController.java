package com.buuttiproject.springAPI.controller;

import com.buuttiproject.springAPI.model.Book;
import com.buuttiproject.springAPI.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping("/books")
    public ResponseEntity<?> postBook(@RequestBody Book newBook) {
        return bookService.postBook(newBook);
    }

    @DeleteMapping("/book")
    public ResponseEntity<?> deleteBook(Integer id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("/book")
    public ResponseEntity<?> changeBook(@RequestBody Book oldBook) {
        return bookService.changeBook(oldBook);
    }
}
