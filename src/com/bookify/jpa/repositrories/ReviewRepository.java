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
    public Review findById(int id) {
        Review r = em.find(Review.class, id);
        return r;
    }

    /** Visar alla reviews för en viss boktitel**/
    public List viewReviewForBook(String bookTitle){
        TypedQuery<Review> reviewQuery =  em.createQuery("SELECT r FROM Review r JOIN r.book b  WHERE b.bookId = r.reviewbookId and b.bookTitel = :bookTitle", Review.class);
        return reviewQuery.setParameter("bookTitle", bookTitle).getResultList();
    }

    //Tar bort en review för en viss titel
    public int removeReview( int reviewId){
        Query removeQuery = em.createQuery("DELETE from Review r where reviewId= :reviewId ");
        return removeQuery.setParameter("reviewId", reviewId).executeUpdate();
    }


    public int updateReview(int reviewId, String newReview){
        Query updateQuery = em.createQuery("UPDATE Review r SET r.reviewText= :newReview where r.reviewId = :reviewId");
        return updateQuery.setParameter("newReview", newReview).setParameter("reviewId", reviewId).executeUpdate();

    }

}
