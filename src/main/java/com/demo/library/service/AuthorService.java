package com.demo.library.service;

import com.demo.library.model.Author;
import com.demo.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Integer authorId){
        return authorRepository.findById(authorId);
    }

    public void addAuthor(Author author){
        authorRepository.save(author);
    }

    public void authorPostDelete(Integer authorId){
        authorRepository.deleteById(authorId);
    }

}
