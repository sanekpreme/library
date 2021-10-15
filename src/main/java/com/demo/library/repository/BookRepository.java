package com.demo.library.repository;

import com.demo.library.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

//    List<Book> findBookByTitle(String title);

}
