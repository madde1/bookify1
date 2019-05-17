package com.bookify.jpa.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "haveread")
@JsonPropertyOrder({"haveReadId", "haveReadBookId", "haveReadUserId", "haveReadFavorite"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="userId")
public class HaveRead implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "haveReadId")
    private int id;

    @Column(name = "haveReadBookId")
    private int bookId;

    @Column(name = "haveReadUserId")
    private int userId;

    @Column(name = "haveReadFavorite")
    private int isFavourite;

    public HaveRead() {
    }

    public HaveRead(int bookId, int userId, int isFavourite) {
        this.bookId = bookId;
        this.userId = userId;
        this.isFavourite = isFavourite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }
}
