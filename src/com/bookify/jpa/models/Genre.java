package com.bookify.jpa.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genre")
@JsonPropertyOrder({"genreId", "genreName"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Genre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "genreId")
    private int id;

    @Column(name = "genreName")
    private String genreNewName;

    public Genre() {
    }

    public Genre(String genreName) {
        this.genreNewName = genreName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return genreNewName;
    }

    public void setName(String genreName) {
        this.genreNewName = genreName;
    }

   /* public Set<Book> getBookSet() {
        return bookSet;
    }*/
}
