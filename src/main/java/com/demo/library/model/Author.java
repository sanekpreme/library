package com.demo.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String AuthorName, AuthorCountry, AuthorDetails;

    public Long getId() {
        return id;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public String getAuthorCountry() {
        return AuthorCountry;
    }

    public String getAuthorDetails() {
        return AuthorDetails;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setAuthorName(String AuthorName) {
        this.AuthorName = AuthorName;
    }

    public void setAuthorCountry(String authorCountry) {
        this.AuthorCountry = authorCountry;
    }

    public void setAuthorDetails(String AuthorDetails) {
        this.AuthorDetails = AuthorDetails;
    }

    public Author(){

    }

    public Author(String AuthorName, String AuthorCountry, String AuthorDetails){

        this.AuthorCountry = AuthorName;
        this.AuthorDetails = AuthorCountry;
        this.AuthorName = AuthorDetails;
    }

}
