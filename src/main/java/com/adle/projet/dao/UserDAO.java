package com.adle.projet.dao;

import java.util.List;

import com.adle.projet.entity.User;

public interface UserDAO {

    public List<User> getUsers();

    public void saveUser( User theUser );

    public User getUser( int theId );

}
