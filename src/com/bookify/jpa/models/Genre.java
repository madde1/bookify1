/**
 * @Authors: Anna, Madeleine, Andreas, Simon, Lucie
 * @version 1.0
 * **/
package com.bookify.jpa.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Model for genre.
 * @see Book
 */

@Entity
@Table(name = "genre")
@JsonPropertyOrder({"genreId", "genreName"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
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

   @Override
   public boolean equals(Object o) {
       if(o.getClass() != this.getClass()) {
           return false;
       }
       Genre g = (Genre)o;

       return this.getName().equals(g.getName());
   }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
