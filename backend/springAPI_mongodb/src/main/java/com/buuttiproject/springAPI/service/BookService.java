package com.buuttiproject.springAPI.service;

import com.buuttiproject.springAPI.model.Book;
import com.buuttiproject.springAPI.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.regex.Pattern;


@Service
public class BookService {

    private BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> checkInputvalues(@RequestBody Book bookData) {
        // handles the validation of inputs
        String bookTitle = bookData.getTitle();
        String bookAuthor = bookData.getAuthor();
        String bookDescription = bookData.getDescription();
        Pattern pattern = Pattern.compile("[^A-Za-z0-9!. ]");
        String statusBody = "";
        System.out.println(bookTitle + bookDescription + bookAuthor);
        if (bookTitle.length() == 0 || bookAuthor.length() == 0 || bookDescription.length() == 0) {

            statusBody = "Input values must be greater than 0";
        }
        if (pattern.matcher(bookAuthor).find() || pattern.matcher(bookTitle).find()) {
            if (statusBody == "") {
                statusBody = "Author and Title must not contain special values other than !";
            } else {
                statusBody = statusBody + "\n" + "Author and Title must not contain special values other than !";
            }
        }
        if (statusBody == "") {
            return new ResponseEntity<>("Book created successfully.", HttpStatus.OK);
        } else {
            System.out.println("statusBody:" + statusBody);
            return new ResponseEntity<>(statusBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Book>> getBooks() {
        // used to find all the books in the database
        System.out.println(this.repository.findAll());
        return ResponseEntity.ok(this.repository.findAll());
    }

    public ResponseEntity<String> deleteBook(Integer id) {
        // used to delete a book by its id
        Optional<Book> book = this.repository.findById(id);
        if (book.isPresent()) {
            this.repository.deleteById(id);
            return ResponseEntity.ok("Successfully deleted book.");
        } else {
            return new ResponseEntity<>("Book with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> postBook(Book newBook) {
        // used to add a book into the database
        if (checkInputvalues(newBook).getStatusCode() == HttpStatus.OK) {
            this.repository.save(newBook);
        }
        return checkInputvalues(newBook);
    }

    public ResponseEntity<?> changeBook(Book changedBook) {
        // used to change values of a book by its id
        Integer id = changedBook.getId();
        Optional<Book> book = this.repository.findById(id);
        if (checkInputvalues(changedBook).getStatusCode() == HttpStatus.OK) {
            if (book.isPresent()) {
                this.repository.save(changedBook);
                return ResponseEntity.ok("Successfully modified book.");
            } else {
                return new ResponseEntity<>("Book with id " + id + "not found", HttpStatus.NOT_FOUND);
            }
        }
        return checkInputvalues(changedBook);
    }
}
