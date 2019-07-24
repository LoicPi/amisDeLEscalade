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

import com.adle.projet.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveRole( Role theRole ) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate( theRole );
    }

    @Override
    public List<Role> getRoles() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery( Role.class );
        Root<Role> root = cq.from( Role.class );
        cq.select( root );
        Query query = session.createQuery( cq );
        return query.getResultList();
    }

    @Override
    public Role findRoleById( int id ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Role theRole = currentSession.get( Role.class, id );
        return theRole;
    }

}
