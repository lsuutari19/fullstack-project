package com.buuttiproject.springAPI.controller;

import com.buuttiproject.springAPI.model.Book;
import com.buuttiproject.springAPI.controller.repository.BookRepository;
import com.buuttiproject.springAPI.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private BookService bookService;
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(this.bookRepository.findAll());
    }

    @PostMapping("/books")
    public ResponseEntity<?> postBook(@RequestBody Book newBook) {
        return ResponseEntity.ok(this.bookRepository.save(newBook));
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
