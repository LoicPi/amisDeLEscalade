package com.adle.projet.service;

import java.util.List;
import java.util.Optional;

import com.adle.projet.entity.User;

public interface UserService {

    public List<User> getUsers();

    public void saveUser( User theUser );

    public User getUser( int theId );

    public Optional<User> findUserWithEmail( String email );

    public Optional<User> findUserWithNickName( String nickName );

    public List<User> findUserByEmail( String email );

    public void updateUser( User theUser );

    public void deleteUser( int theId );

    public void updatePasswordUser( User theUser );

}
