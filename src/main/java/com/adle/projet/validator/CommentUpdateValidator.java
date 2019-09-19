package com.adle.projet.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.dto.UpdateComment;

@Component
public class CommentUpdateValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( CommentUpdateValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return UpdateComment.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {
        UpdateComment updateComment = (UpdateComment) obj;

        if ( updateComment.getUpdateContents().contentEquals( "" ) ) {
            logger.info( "UpdateComment_UpdateContents is empty" );
            errors.rejectValue( "updateCommentContents", "commentValidator.commentContents.empty" );
        }

        if ( updateComment.getUpdateContents().length() > 300 ) {
            logger.info( "UpdateComment_UpdateContents is too long" );
            errors.rejectValue( "updateCommentContents", "updateCommentValidator.updateCommentContents.tooLong" );
        }
    }

}
