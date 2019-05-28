package com.bookify.jpa.repositrories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.bookify.jpa.models.ApiUsers;

public class ApiUsersRepository {
    @PersistenceContext(unitName = "BookifyPU")
    private EntityManager em;

    public ApiUsers findByApiUserName(String userName) {
        TypedQuery<ApiUsers> queUserName = em.createQuery("SELECT u FROM ApiUsers u WHERE u.apiUserName = :userName", ApiUsers.class);
        ApiUsers u = queUserName.setParameter("userName", userName).getSingleResult();
        return u;
    }
}
