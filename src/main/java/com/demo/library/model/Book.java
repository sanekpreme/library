package com.demo.library.model;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title, author, binding, isbn, yearOfPublishing;
    private int circulation;
//    private int categoryId;

//    public int getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(int categoryId) {
//        this.categoryId = categoryId;
//    }

//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }

    private String filename;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "category_id", referencedColumnName = "category_id")

//    private Category category;

    public Book() {
    }

    public Book(String title, String author, String binding, String isbn, String yearOfPublishing, int circulation, User user) {
        this.title = title;
        this.author = author;
        this.binding = binding;
        this.isbn = isbn;
        this.yearOfPublishing = yearOfPublishing;
        this.circulation = circulation;
        this.postAuthor = user;
//        this.categoryId = categoryId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User postAuthor;

    public String getPostAuthorName(){
        return postAuthor != null ? postAuthor.getUsername() : "<none>";
    }

    public User getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(User postAuthor) {
        this.postAuthor = postAuthor;
    }

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
