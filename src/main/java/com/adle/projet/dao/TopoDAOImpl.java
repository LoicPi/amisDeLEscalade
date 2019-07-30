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

import com.adle.projet.entity.Topo;

@Repository
public class TopoDAOImpl implements TopoDAO {

    private static final Logger logger = LogManager.getLogger( UserDAOImpl.class );

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
        currentSession.saveOrUpdate( topo );
        logger.info( "Topo saved successfully, Topo details = " + topo );
    }

    @Override
    public Topo getTopo( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Topo topo = currentSession.get( Topo.class, theId );
        logger.info( "Topo loaded successfully, Topo details = " + topo );
        return topo;
    }

    @Override
    public void updateTopo( Topo topo ) {
        Session currentsession = sessionFactory.getCurrentSession();
        currentsession.update( topo );
        logger.info( "Topo updated successfully, Topo details = " + topo );

    }
}
