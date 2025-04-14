package com.example.auth.service;

import com.example.auth.dao.AuthDao;
import com.example.auth.entity.BsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements UserDetailsService {

    @Autowired
    private AuthDao authDao;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        BsUser user = authDao.findByUserId(userId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + userId);
        }

        // Grant authority based on user_role column
        return new org.springframework.security.core.userdetails.User(
                user.getUserId(),
                user.getPassword(), // make sure password is hashed
                Collections.singletonList(new SimpleGrantedAuthority(user.getUserRole()))
        );
    }
}


xxxxxx

package com.example.auth.dao;

import com.example.auth.entity.BsUser;

public interface AuthDao {
    BsUser findByUserId(String userId);
}

xxxxx



package com.example.auth.dao.impl;

import com.example.auth.dao.AuthDao;
import com.example.auth.entity.BsUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AuthDaoImpl implements AuthDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BsUser findByUserId(String userId) {
        try {
            return entityManager
                    .createQuery("SELECT u FROM BsUser u WHERE u.userId = :userId", BsUser.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}