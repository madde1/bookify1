/**
 * @Authors: Anna, Madeleine, Andreas, Simon, Lucie
 * @version 1.0
 * **/
package com.bookify.jpa.repositrories;

import com.bookify.jpa.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for users.
 * @see User
 */

public class UserRepository {

    @PersistenceContext(unitName = "BookifyPU")
    private EntityManager em;

    public List<User> getAllUsers(){
        Query query = em.createQuery("SELECT user FROM User user order by user.userName");
        List<User> users = new ArrayList<>();
        for(Object u : query.getResultList()){
            users.add(((User)u));
        }
     return users;
    }

    public User findById(int id) {
        User u = em.find(User.class, id);
        return u;
    }

    public User findUserByUsernameOrId(String userName){
        TypedQuery<User> queUserName = em.createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class);
        if(queUserName.setParameter("userName",userName).getResultList().size() > 0) {
            User u = queUserName.setParameter("userName", userName).getSingleResult();
            return u;
        } else {
            return findById(Integer.parseInt(userName));
        }
    }

    public User create(User user){
        em.persist(user);
        return user;
    }


    public EntityManager getEm() {
        return em;
    }

    public void delete(User user) {
        em.remove(user);
    }

}



