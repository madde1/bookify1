/**
 * @Authors: Anna, Madeleine, Andreas, Simon, Lucie
 * @version 1.0
 * **/
package com.bookify.jpa.repositrories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.bookify.jpa.models.ApiUsers;

/**
 * Repository for Api user table.
 */

public class ApiUsersRepository {
    @PersistenceContext(unitName = "BookifyPU")
    private EntityManager em;

    public ApiUsers findByApiUserName(String userName) {
        TypedQuery<ApiUsers> queUserName = em.createQuery("SELECT u FROM ApiUsers u WHERE u.apiUserName = :userName", ApiUsers.class);
        ApiUsers u = queUserName.setParameter("userName", userName).getSingleResult();
        return u;
    }
}
