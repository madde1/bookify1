/**
 * @Authors: Anna, Madeleine, Andreas, Simon, Lucie
 * @version 1.0
 * **/
package com.bookify.jpa.models;


import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.enterprise.context.SessionScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.*;

/**
 * Model for a book object.
 * Have data for a book, genre and users that have read the book.
 */

@Entity
@Table(name = "book")
@JsonPropertyOrder({"bookId","bookTitel","bookAuthor","bookDate", "booksGenre"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="bookId")
@SessionScoped
public class Book implements Serializable {

    public Book(String bookTitel, String bookAuthor, Date bookDate) {
        this.bookTitel = bookTitel;
        this.bookAuthor = bookAuthor;
        this.bookDate = bookDate;
    }
    public Book(){}
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "bookId")
    private  Integer bookId;

    @Column(name = "bookTitel")
    private String bookTitel;

    @Column(name = "bookAuthor")
    private String bookAuthor;

    @Column(name = "bookDate")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date bookDate;
    
    @ManyToMany( fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    @JoinTable(name = "bookgen",
            joinColumns = @JoinColumn(name = "bookGenBId"),
            inverseJoinColumns = @JoinColumn(name = "bookGenGId"))
    private Set<Genre> booksGenre;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<HaveRead> haveReads;


    

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "reviewbookId")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Review> reviewList = new ArrayList<Review>();

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    /*
    //Checks if the reviewId exists in the list
    public boolean reviewQuery(int reviewId) {
        return getReviewList().contains(reviewId);
    }

    //Removes the review from the list
    public void removeReview(Review review) {
        reviewList.remove(review);
    }
*/
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

    public Set<Genre> getBooksGenre() {
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

    @Override
    public int hashCode() {
        return getBookId().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(o.getClass() != this.getClass()) {
            return false;
        }
        Book b = (Book)o;

        return this.getBookId() == b.getBookId();
    }
}