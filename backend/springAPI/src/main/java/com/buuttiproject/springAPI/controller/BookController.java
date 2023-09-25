package com.buuttiproject.springAPI.controller;

import com.buuttiproject.springAPI.model.Book;
import com.buuttiproject.springAPI.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

@RestController
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ArrayList getBooks() {
        return bookService.getBooks();
    }

    @PostMapping("/books")
    public ResponseEntity<?> postBook(@RequestBody Book newBook) {
        return ResponseEntity.ok("asd");
    }

    @DeleteMapping("/book")
    public void deleteBook(@RequestParam Integer id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/book")
    public void changeBook(@RequestBody Book oldBook) {
        bookService.changeBook(oldBook);
    }
}
