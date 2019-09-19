package com.adle.projet.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.entity.Comment;

@Component
public class CommentValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( CommentValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return Comment.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {

        Comment comment = (Comment) obj;

        if ( comment.getContents().contentEquals( "" ) ) {
            logger.info( "Comment_Contents is empty" );
            errors.rejectValue( "contents", "commentValidator.commentContents.empty" );
        }
    }
}
