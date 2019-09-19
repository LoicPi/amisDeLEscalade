package com.adle.projet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.adle.projet.entity.County;

@Repository
public class CountyDAOImpl implements CountyDAO {

    private static final Logger logger = LogManager.getLogger( CountyDAOImpl.class );

    @Autowired
    private SessionFactory      sessionFactory;

    @Override
    public List<County> getCountys() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<County> cq = cb.createQuery( County.class );
        Root<County> root = cq.from( County.class );
        cq.select( root );
        cq.orderBy( cb.asc( root.get( "id" ) ) );
        Query query = session.createQuery( cq );
        logger.info( "County List : " + query.getResultList() );
        return query.getResultList();
    }

    @Override
    public County getCounty( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        County county = currentSession.get( County.class, theId );
        logger.info( "County loaded successfully, County details = " + county );
        return county;
    }

    @Override
    public Map<Integer, String> getCountyNameOfCountys( List<County> countys ) {
        Map<Integer, String> countyName = new HashMap<Integer, String>();
        for ( int i = 1; i <= ( countys.size() - 1 ); i++ ) {
            countyName.put( i, countys.get( i - 1 ).getCountyName() );
        }
        logger.info( "MapOfCounty : " + countyName );
        return countyName;
    }

}
