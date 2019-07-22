package com.adle.projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adle.projet.dao.UserDAO;
import com.adle.projet.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public void saveUser( User theUser ) {
        userDAO.saveUser( theUser );

    }

    public User getUser( int theId ) {
        return userDAO.getUser( theId );
    }

    @Override
    public Optional<User> findUserWithEmail( String email ) {
        return userDAO.getUsers().stream()
                .filter( user -> user.getEmail().equals( email ) )
                .findFirst();
    }

    @Override
    public Optional<User> findUserWithNickName( String nickName ) {
        return userDAO.getUsers().stream()
                .filter( user -> user.getNickName().equals( nickName ) )
                .findFirst();
    }

}