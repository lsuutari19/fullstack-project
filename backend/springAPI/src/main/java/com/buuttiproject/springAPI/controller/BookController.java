package com.buuttiproject.springAPI.controller;

import com.buuttiproject.springAPI.model.Book;
import com.buuttiproject.springAPI.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public Book getBook(@RequestParam Integer id) {
        Optional book = bookService.getBook(id);
        if(book.isPresent()){
            return (Book) book.get();
        }
        return null;
    }

    @GetMapping("/books")
    public ArrayList getBooks() {
        return bookService.getBooks();
    }

    @PostMapping("/books")
    public ResponseEntity<?> postBook(@RequestBody Book newBook) {
        ResponseEntity<?> response = bookService.postBook(newBook);
        return response;
    }

    @DeleteMapping("/books")
    public void deleteBook(@RequestParam Integer id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/book")
    public void changeBook(@RequestBody Book oldBook) {
        bookService.changeBook(oldBook);
    }
}
