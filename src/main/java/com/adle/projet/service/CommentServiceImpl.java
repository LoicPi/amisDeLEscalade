package com.adle.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adle.projet.dao.CommentDAO;
import com.adle.projet.entity.Comment;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    public CommentDAO commentDAO;

    @Override
    public List<Comment> getComments() {
        return commentDAO.getComments();
    }

    @Override
    public void saveComment( Comment comment ) {
        commentDAO.saveComment( comment );

    }

    @Override
    public void updateComment( Comment comment ) {
        commentDAO.updateDAO( comment );

    }

    @Override
    public void deleteComment( int theId ) {
        commentDAO.deleteComment( theId );

    }

    @Override
    public Comment getComment( int theId ) {
        return commentDAO.getComment( theId );
    }

    @Override
    public List<Comment> findCommentByUserId( int userId ) {
        return commentDAO.findCommentByUserId( userId );
    }

    @Override
    public List<Comment> findCommentBySpotId( int spotId ) {
        return commentDAO.findCommentBySpotId( spotId );
    }

}
