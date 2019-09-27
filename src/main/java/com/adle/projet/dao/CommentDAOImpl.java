package com.adle.projet.dao;

import java.sql.Timestamp;
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

import com.adle.projet.entity.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {

    private static final Logger logger = LogManager.getLogger( CountyDAOImpl.class );

    @Autowired
    private SessionFactory      sessionFactory;

    @Override
    public List<Comment> getComments() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Comment> cq = cb.createQuery( Comment.class );
        Root<Comment> root = cq.from( Comment.class );
        cq.select( root );
        Query query = session.createQuery( cq );
        logger.info( "Comment List : " + query.getResultList() );
        return query.getResultList();
    }

    @Override
    public void saveComment( Comment comment ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Timestamp now = new Timestamp( System.currentTimeMillis() );
        comment.setDate( now );
        currentSession.saveOrUpdate( comment );
        logger.info( "Comment saved successfully, Comment details = " + comment );
    }

    @Override
    public void updateDAO( Comment comment ) {
        Session currentsession = sessionFactory.getCurrentSession();
        currentsession.update( comment );
        logger.info( "Comment updated successfully, Comment details = " + comment );
    }

    @Override
    public void deleteComment( int theId ) {
        Session session = sessionFactory.getCurrentSession();
        Comment theComment = session.byId( Comment.class ).load( theId );
        session.delete( theComment );
    }

    @Override
    public Comment getComment( int theId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Comment> query = currentSession.createNamedQuery( "Comment_findById", Comment.class );
        query.setParameter( "commentId", theId );
        Comment commentResult = (Comment) query.getSingleResult();
        Hibernate.initialize( commentResult.getUser() );
        logger.info( "Comment loaded successfully, Comment details = " + commentResult );
        return commentResult;
    }

    @Override
    public List<Comment> findCommentByUserId( int userId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Comment> query = currentSession.createNamedQuery( "Comment_findByUserId", Comment.class );
        query.setParameter( "user", userId );
        List<Comment> commentResult = query.getResultList();
        logger.info( "Comment List : " + commentResult );
        return commentResult;
    }

    @Override
    public List<Comment> findCommentBySpotId( int spotId ) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Comment> query = currentSession.createNamedQuery( "Comment_findBySpotId", Comment.class );
        query.setParameter( "spot", spotId );
        List<Comment> commentResult = query.getResultList();
        logger.info( "Comment List : " + commentResult );
        return commentResult;
    }

}
