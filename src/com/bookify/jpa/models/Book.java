package com.bookify.jpa.models;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "book")
@JsonPropertyOrder({"bookId","bookTitel","bookAuthor","bookDate", "booksGenre"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="bookId")

public class Book implements Serializable {

    public Book(String bookTitel, String bookAuthor, Date bookDate) {
        this.bookTitel = bookTitel;
        this.bookAuthor = bookAuthor;
        this.bookDate = bookDate;
    }
    public Book(){}
    @Id
    @GeneratedValue
    @Column(name = "bookId")
    private  Integer bookId;

    @Column(name = "bookTitel")
    private String bookTitel;

    @Column(name = "bookAuthor")
    private String bookAuthor;

    @Column(name = "bookDate")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")

    @ManyToMany( fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(name = "bookgen",
            joinColumns = @JoinColumn(name = "bookGenBId"),
            inverseJoinColumns = @JoinColumn(name = "bookGenGId"))
    private List<Genre> booksGenre;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<HaveRead> haveReads;

    private Date bookDate;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookTitel() {
        return bookTitel;
    }

    public void setBookTitel(String bookTitel) {
        this.bookTitel = bookTitel;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public List<Genre> getBooksGenre() {
        return booksGenre;
    }

    public Set<HaveRead> getHaveReads() {
        return haveReads;
    }

    public boolean isFavourite(int userId) {
        for(HaveRead hr : haveReads) {
            if(hr.getIsFavourite() == 1 && hr.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }
}