package com.demo.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer authorId;

    public Integer getId() {
        return authorId;
    }

    public void setId(Integer authorId) {
        this.authorId = authorId;
    }

    private String authorCountry, authorDetails;

    private String authorName;

    private String filename;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("author")
    private List<Book> bookList = new ArrayList<>();

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorCountry() {
        return authorCountry;
    }

    public String getAuthorDetails() {
        return authorDetails;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAuthorCountry(String authorCountry) {
        this.authorCountry = authorCountry;
    }

    public void setAuthorDetails(String authorDetails) {
        this.authorDetails = authorDetails;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Author() {

    }

    public Author(String authorName, String authorCountry, String authorDetails) {

        this.authorCountry = authorName;
        this.authorDetails = authorCountry;
        this.authorName = authorDetails;
    }

}
