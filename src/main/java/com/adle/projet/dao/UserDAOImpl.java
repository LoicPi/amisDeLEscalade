package com.adle.projet.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adle.projet.entity.User;
import com.adle.projet.tools.PasswordEncryptor;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Function return the list of users in database
     */
    public List<User> getUsers() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery( User.class );
        Root<User> root = cq.from( User.class );
        cq.select( root );
        Query query = session.createQuery( cq );
        return query.getResultList();
    }

    /**
     * Function save a user in database
     */
    public void saveUser( User theUser ) {
        Session currentSession = sessionFactory.getCurrentSession();
        theUser.setPassword( PasswordEncryptor.hashPassword( theUser.getPassword() ) );
        currentSession.saveOrUpdate( theUser );
    }

    /**
     * Function return a user by the given id
     */
    public User getUser( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        User theUser = currentSession.get( User.class, theId );
        return theUser;
    }

    @Override
    public List<User> findUserByEmail( String email ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createNamedQuery( "User_findByEmail", User.class );
        query.setParameter( "email", email );
        List<User> userResult = query.getResultList();
        return userResult;
    }

}
