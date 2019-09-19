package com.adle.projet.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.dto.UpdatePath;

@Component
public class PathUpdateValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( PathUpdateValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return UpdatePath.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {

        UpdatePath updatePath = (UpdatePath) obj;

        if ( updatePath.getUpdatePathName().equals( "" ) ) {
            logger.info( "UpdatePath_Name is empty" );
            errors.rejectValue( "updatePathName", "pathValidator.pathName.empty" );
        }

        if ( updatePath.getUpdatePathName().length() > 100 || updatePath.getUpdatePathName().length() < 3 ) {
            logger.info( "Length of UpdatePath_Name is not correct." );
            errors.rejectValue( "updatePathName", "path.name.invalid" );
        }

        if ( updatePath.getPathType() == null ) {
            logger.info( "Path_Type is empty" );
            errors.rejectValue( "pathType", "pathValidator.pathType.empty" );
        }
    }

}
