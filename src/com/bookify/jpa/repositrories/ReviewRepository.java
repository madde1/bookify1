package com.bookify.jpa.repositrories;

import com.bookify.jpa.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ReviewRepository {

    @PersistenceContext(unitName = "BookifyPU")
    private EntityManager em;

    public List<User> getAllReviews(){
        Query query = em.createQuery("SELECT r FROM Review r order by r.reviewDate");
        return query.getResultList();
    }
}
