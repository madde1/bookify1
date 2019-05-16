package com.bookify.jpa.repositrories;

import com.bookify.jpa.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserRepository {

    @PersistenceContext(unitName = "BookifyPU")
    private EntityManager em;

    public List<User> getAllUsers(){
        Query query = em.createQuery("SELECT user FROM User user order by user.userName");
     return query.getResultList();
    }

    public User findById(int id) {
        return em.find(User.class, id);
    }

    public User findByUserName(String userName){
        TypedQuery<User> queUserName = em.createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class);
        return queUserName.setParameter("userName",userName).getSingleResult();
    }

    public User create(User user){
        em.persist(user);
        return user;
    }

}
