package com.bookify.jpa.repositrories;

import com.bookify.jpa.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class UserRepository {

    @PersistenceContext(unitName = "BookifyPU")
    private EntityManager em;

    public List<User> getAllUsers(){
        Query query = em.createQuery("SELECT user FROM User user order by user.userName");
     return query.getResultList();
    }
}
