package com.adle.projet.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adle.projet.entity.Comment;
import com.adle.projet.entity.Length;
import com.adle.projet.entity.Path;
import com.adle.projet.entity.Sector;
import com.adle.projet.entity.Spot;
import com.adle.projet.entity.Topo;
import com.adle.projet.entity.User;
import com.adle.projet.tools.FormattingString;
import com.adle.projet.tools.PasswordEncryptor;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger logger           = LogManager.getLogger( UserDAOImpl.class );

    FormattingString            formattingString = new FormattingString();

    @Autowired
    private SessionFactory      sessionFactory;

    /**
     * Function return the list of users in database
     */
    @Override
    public List<User> getUsers() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery( User.class );
        Root<User> root = cq.from( User.class );
        cq.select( root );
        Query query = session.createQuery( cq );
        logger.info( "Person List : " + query.getResultList() );
        return query.getResultList();
    }

    /**
     * Function save a user in database
     */
    @Override
    public void saveUser( User user ) {
        Session currentSession = sessionFactory.getCurrentSession();
        user.setFirstName( formattingString.Formatting( user.getFirstName() ) );
        user.setLastName( formattingString.Formatting( user.getLastName() ) );
        user.setPassword( PasswordEncryptor.hashPassword( user.getPassword() ) );
        currentSession.saveOrUpdate( user );
        logger.info( "User saved successfully, User details = " + user );
    }

    /**
     * Function return a user by the given id
     */
    @Override
    public User getUser( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createNamedQuery( "User_findById", User.class );
        query.setParameter( "userId", theId );
        List<User> userResult = query.getResultList();
        if ( userResult.size() > 0 ) {
            logger.info( "User loaded successfully, User details = " + userResult );
            return userResult.get( 0 );
        }
        return null;

    }

    /**
     * Function find a User with email return a list of user
     */
    @Override
    public List<User> findUserByEmail( String email ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createNamedQuery( "User_findByEmail", User.class );
        query.setParameter( "email", email );
        List<User> userResult = query.getResultList();
        logger.info( "List of User find by email : " + userResult );
        return userResult;
    }

    /**
     * Function update a User in database
     */
    @Override
    public void updateUser( User user ) {
        Session currentsession = sessionFactory.getCurrentSession();
        user.setFirstName( formattingString.Formatting( user.getFirstName() ) );
        user.setLastName( formattingString.Formatting( user.getLastName() ) );
        currentsession.update( user );
        logger.info( "User updated successfully, User details = " + user );
    }

    /**
     * Function update a User's password in database
     */
    @Override
    public void updatePasswordUser( User user ) {
        Session currentSession = sessionFactory.getCurrentSession();
        user.setPassword( PasswordEncryptor.hashPassword( user.getPassword() ) );
        currentSession.update( user );
        logger.info( "User updated successfully, User details = " + user );
    }

    /**
     * Function delete a User in database but keep is spot, sector, path and
     * length
     */
    @Override
    public void deleteUser( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        User theUser = currentSession.byId( User.class ).load( theId );
        for ( Spot spot : theUser.getSpots() ) {
            spot.setUser( null );
            currentSession.saveOrUpdate( spot );
        }
        for ( Sector sector : theUser.getSectors() ) {
            sector.setUser( null );
            currentSession.saveOrUpdate( sector );
        }
        for ( Path path : theUser.getPaths() ) {
            path.setUser( null );
            currentSession.saveOrUpdate( path );
        }
        for ( Length length : theUser.getLengths() ) {
            length.setUser( null );
            currentSession.saveOrUpdate( length );
        }
        for ( Comment comment : theUser.getComments() ) {
            comment.setUser( null );
            currentSession.saveOrUpdate( comment );
        }
        for ( Topo topo : theUser.getTopos() ) {
            currentSession.delete( topo );
        }
        logger.info( "User delete : " + theUser );
        currentSession.delete( theUser );
    }

}
