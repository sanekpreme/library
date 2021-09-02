package com.demo.library.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title, author, binding, isbn, year_of_publishing;
    private int circulation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getYear_of_publishing() {
        return year_of_publishing;
    }

    public void setYear_of_publishing(String year_of_publishing) {
        this.year_of_publishing = year_of_publishing;
    }

    public int getCirculation() {
        return circulation;
    }

    public void setCirculation(int circulation) {
        this.circulation = circulation;
    }

    public Book() {
    }

    public Book(String title, String author, String binding, String isbn, String year_of_publishing, int circulation) {
        this.title = title;
        this.author = author;
        this.binding = binding;
        this.isbn = isbn;
        this.year_of_publishing = year_of_publishing;
        this.circulation = circulation;
    }
}
