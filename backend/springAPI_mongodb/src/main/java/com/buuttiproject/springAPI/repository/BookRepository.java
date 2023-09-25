package com.buuttiproject.springAPI.repository;

import com.buuttiproject.springAPI.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer> {

}
