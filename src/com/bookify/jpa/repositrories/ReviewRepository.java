package com.bookify.jpa.repositrories;

import com.bookify.jpa.models.Review;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReviewRepository {

    @PersistenceContext(unitName = "BookifyPU")
    private EntityManager em;

    public List<Review> getAllReviews(){
        Query query = em.createQuery("SELECT r FROM Review r order by r.reviewDate");
        return query.getResultList();
    }
    public Review create(Review review){
        em.persist(review);
        return review;
    }

    /** Visar alla reviews för en viss boktitel**/
    public List viewReviewForBook(String bookTitle){
        TypedQuery<Review> reviewQuery =  em.createQuery("SELECT r FROM Review r JOIN r.book b  WHERE b.bookId = r.reviewbookId and b.bookTitel = :bookTitle", Review.class);
        return reviewQuery.setParameter("bookTitle", bookTitle).getResultList();
    }

    //Tar bort en review för en viss titel
    public int removeReview(String bookTitle){
        Query removeQuery = em.createQuery("DELETE from Review r where reviewbookId = book.bookId and book.bookTitel= :bookTitle");
        return removeQuery.setParameter("bookTitle", bookTitle).executeUpdate();
    }


}
