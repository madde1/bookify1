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

/**
 * Model for books that users have read, also saves if its a favourite book.
 * @see Book
 */

@Entity
@IdClass(HaveReadId.class)
@Table(name = "haveread")
@JsonPropertyOrder({"haveReadBookId", "haveReadUserId", "haveReadFavorite"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="userId")
public class HaveRead implements Serializable {
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "haveReadId")
    private int id;*/

    //@Column(name = "haveReadBookId")
    //private int bookId;

    @Id
    @Column(name = "haveReadUserId")
    private int userId;

    @Column(name = "haveReadFavorite")
    private int isFavourite;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "haveReadBookId")
    private Book book;

    public HaveRead() {
    }

    public HaveRead(int bookId, int userId, int isFavourite) {
        this.userId = userId;
        this.isFavourite = isFavourite;
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
