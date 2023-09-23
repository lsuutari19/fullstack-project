package com.buuttiproject.springAPI.service;

import com.buuttiproject.springAPI.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class BookService {

    private List<Book> bookList;

    public BookService() {
        bookList = new ArrayList<>();
        // TO-DO implement mongodb queries here to get all books from the database

        Book book = new Book("author", "title", "description");
        Book book1 = new Book("luri", "lurin title", "lurin description");
        bookList.addAll(Arrays.asList(book, book1));
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
    public List<Book> postBook(Book newBook) {
        System.out.println(newBook);
        bookList.add(newBook);
        return bookList;
    }
}
