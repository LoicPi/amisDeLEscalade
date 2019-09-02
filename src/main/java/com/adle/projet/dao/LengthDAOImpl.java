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

import com.adle.projet.entity.Length;

@Repository
public class LengthDAOImpl implements LengthDAO {

    private static final Logger logger = LogManager.getLogger( LengthDAOImpl.class );

    @Autowired
    private SessionFactory      sessionFactory;

    @Override
    public List<Length> getLengths() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Length> cq = cb.createQuery( Length.class );
        Root<Length> root = cq.from( Length.class );
        cq.select( root );
        Query query = session.createQuery( cq );
        logger.info( "Length List : " + query.getResultList() );
        return query.getResultList();
    }

    @Override
    public void saveLength( Length length ) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate( length );
        logger.info( "Length saved successfully, Length details = " + length );

    }

    @Override
    public void updateLength( Length length ) {
        Session currentsession = sessionFactory.getCurrentSession();
        currentsession.update( length );
        logger.info( "Length updated successfully, Length details = " + length );

    }

    @Override
    public void deletePath( int theId ) {
        Session session = sessionFactory.getCurrentSession();
        Length theLength = session.byId( Length.class ).load( theId );
        session.delete( theLength );

    }

    @Override
    public Length getLength( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Length length = currentSession.get( Length.class, theId );
        logger.info( "Length loaded successfully, Length details = " + length );
        return length;
    }

    @Override
    public List<Length> findLengthByUserId( int userId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Length> query = currentSession.createNamedQuery( "Length_findByUserId", Length.class );
        query.setParameter( "userId", userId );
        List<Length> lengthResult = query.getResultList();
        logger.info( "Length List : " + query.getResultList() );
        return lengthResult;
    }

    @Override
    public List<Length> findLengthByPathId( int pathId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Length> query = currentSession.createNamedQuery( "Length_findByPathId", Length.class );
        query.setParameter( "pathId", pathId );
        List<Length> lengthResult = query.getResultList();
        logger.info( "Length List : " + query.getResultList() );
        return lengthResult;
    }

}