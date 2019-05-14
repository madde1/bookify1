package com.bookify.jpa.repositrories;

import com.bookify.jpa.models.Review;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
}
