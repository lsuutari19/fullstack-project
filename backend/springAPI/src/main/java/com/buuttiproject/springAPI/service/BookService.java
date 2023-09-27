package com.buuttiproject.springAPI.service;

import com.buuttiproject.springAPI.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.regex.Pattern;


@Service
public class BookService {

    private List<Book> bookList;

    @Autowired
    public BookService() {
        bookList = new ArrayList<>();
        Book book = new Book("author", "title", "description");
        Book book1 = new Book("luri", "lurin title", "lurin description");
        bookList.addAll(Arrays.asList(book, book1));

    }

    public ResponseEntity<?> checkInputvalues(@RequestBody Book bookData) {
        // Checks that the input values conform to the rules
        // TO-DO check that inputs are not numbers
        String bookTitle = bookData.getTitle();
        String bookAuthor = bookData.getAuthor();
        String bookDescription = bookData.getDescription();
        Pattern pattern = Pattern.compile("[^A-Za-z0-9!]");
        String statusBody = "";

        if(bookTitle.length() == 0 || bookAuthor.length() == 0 || bookDescription.length() == 0) {

            statusBody = "Input values must be greater than 0";
        }
        if (pattern.matcher(bookAuthor).find() || pattern.matcher(bookTitle).find()) {
            if(statusBody == "") {
                statusBody = "Author and Title must not contain special values other than !";
            } else {
                statusBody = statusBody + "\n" + "Author and Title must not contain special values other than !";
            }
        } else {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        System.out.println("statusBody:" + statusBody);
        return new ResponseEntity<>(statusBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public Optional<Book> getBook(Integer id) {
        Optional optional = Optional.empty();
        for(Book book: bookList) {
            if(id == book.getId()){
                optional = Optional.of(book);
                return optional;
            }
        }
        return optional;
    }

    // This becomes obsolent after MongoDB is set up
    public void deleteBook(Integer id) {
        Iterator<Book> iterator = bookList.iterator();
        while(iterator.hasNext()) {
            Book book = iterator.next();
            if(id.equals(book.getId())) {
                iterator.remove();
                return;
            }
        }
    }

    public ArrayList getBooks() {
        System.out.println(bookList);
        return (ArrayList) bookList;
    }

    // TO-DO implement POST request to add books to database
    public ResponseEntity<?> postBook(Book newBook) {
        if(checkInputvalues(newBook).getStatusCode() == HttpStatus.OK) {
            bookList.add(newBook);
        }
        return checkInputvalues(newBook);
    }

    public void changeBook(Book changedBook) {
        Iterator<Book> iterator = bookList.iterator();
        System.out.println(changedBook.getTitle() + changedBook.getId() + changedBook.getAuthor());
        while(iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println("Old book id:" + book.getId());
            System.out.println("Changed book id:" + changedBook.getId());
            if(book.getId() == changedBook.getId()) {
                book.setTitle(changedBook.getTitle());
                book.setAuthor(changedBook.getAuthor());
                book.setDescription(changedBook.getDescription());
                System.out.println("New book title:" + book.getTitle());
                return;
            }
        }
    }
}
