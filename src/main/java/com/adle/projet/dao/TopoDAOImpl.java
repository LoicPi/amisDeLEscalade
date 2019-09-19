package com.adle.projet.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adle.projet.entity.Topo;
import com.adle.projet.tools.FormattingString;

@Repository
public class TopoDAOImpl implements TopoDAO {

    private static final Logger logger           = LogManager.getLogger( TopoDAOImpl.class );

    FormattingString            formattingString = new FormattingString();

    @Autowired
    private SessionFactory      sessionFactory;

    @Override
    public List<Topo> getTopos() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Topo> cq = cb.createQuery( Topo.class );
        Root<Topo> root = cq.from( Topo.class );
        cq.select( root );
        Query query = session.createQuery( cq );
        logger.info( "Topo List : " + query.getResultList() );
        return query.getResultList();
    }

    @Override
    public void saveTopo( Topo topo ) {
        Session currentSession = sessionFactory.getCurrentSession();
        topo.setTopoName( formattingString.Formatting( topo.getTopoName() ) );
        topo.setTopoCity( formattingString.Formatting( topo.getTopoCity() ) );
        topo.setTopoCountry( formattingString.Formatting( topo.getTopoCountry() ) );
        currentSession.saveOrUpdate( topo );
        logger.info( "Topo saved successfully, Topo details = " + topo );
    }

    @Override
    public Topo getTopo( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Topo> query = currentSession.createNamedQuery( "Topo_findById", Topo.class );
        query.setParameter( "topoId", theId );
        Topo topoResult = (Topo) query.getSingleResult();
        Hibernate.initialize( topoResult.getCounty() );
        logger.info( "Topo loaded successfully, Topo details = " + topoResult );
        return topoResult;
    }

    @Override
    public void updateTopo( Topo topo ) {
        Session currentsession = sessionFactory.getCurrentSession();
        topo.setTopoName( formattingString.Formatting( topo.getTopoName() ) );
        topo.setTopoCity( formattingString.Formatting( topo.getTopoCity() ) );
        topo.setTopoCountry( formattingString.Formatting( topo.getTopoCountry() ) );
        currentsession.update( topo );
        logger.info( "Topo updated successfully, Topo details = " + topo );
    }

    @Override
    public List<Topo> findTopoByUserId( int userId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Topo> query = currentSession.createNamedQuery( "Topo_findByUserId", Topo.class );
        query.setParameter( "user", userId );
        List<Topo> topoResult = query.getResultList();
        logger.info( "Topo List : " + query.getResultList() );
        return topoResult;
    }

    @Override
    public void deleteTopo( int theId ) {
        Session session = sessionFactory.getCurrentSession();
        Topo theTopo = session.byId( Topo.class ).load( theId );
        session.delete( theTopo );
    }

}
