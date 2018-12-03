/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.supinfo.rmt.services;

import com.supinfo.rmt.entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 *
 * @author Binarymachine
 */
@Stateless
public class UserService {
    
    @PersistenceContext
    private EntityManager em;

    public Optional<User> login(String username, String password){
        
        try {
            return Optional.of(em.createQuery("SELECT u FROM User u "
                    + "WHERE u.username=:username AND u.password=:password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult());
        } catch(NoResultException e) {
            return Optional.empty();
        }
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}