package com.demo.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookId;

    private String binding, isbn, yearOfPublishing;

    private double price;

    @Column(name = "book_title")
    private String title;

    private int circulation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId", referencedColumnName = "authorId")
    @JsonIgnoreProperties("bookList")

    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    private String description;

    private String filename;

    public Book() {
    }

    public Book(String title, String description, String binding, String isbn, String yearOfPublishing, int circulation, double price, User user) {
        this.title = title;
        this.description = description;
        this.binding = binding;
        this.isbn = isbn;
        this.yearOfPublishing = yearOfPublishing;
        this.circulation = circulation;
        this.postAuthor = user;
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User postAuthor;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPostAuthorName() {
        return postAuthor != null ? postAuthor.getUsername() : "<none>";
    }

    public User getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(User postAuthor) {
        this.postAuthor = postAuthor;
    }

    public Integer getId() {
        return bookId;
    }

    public void setId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(String yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public int getCirculation() {
        return circulation;
    }

    public void setCirculation(int circulation) {
        this.circulation = circulation;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
