package com.adle.projet.dao;

import java.util.List;

import com.adle.projet.entity.User;

public interface UserDAO {

    public List<User> getUsers();

    public void saveUser( User theUser );

    public User getUser( int theId );

    public List<User> findUserByEmail( String email );

    public void updateUser( User theUser );

    public void deleteUser( int theId );

    public void updatePasswordUser( User user );
}
