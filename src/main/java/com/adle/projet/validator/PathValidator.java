package com.adle.projet.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.entity.Path;

@Component
public class PathValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( PathValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return Path.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {

        Path path = (Path) obj;

        if ( path.getPathName().equals( "" ) ) {
            logger.info( "Path_Name is empty" );
            errors.rejectValue( "pathName", "pathValidator.pathName.empty" );
        }

        if ( path.getPathType() == null ) {
            logger.info( "Path_Type is empty" );
            errors.rejectValue( "pathType", "pathValidator.pathType.empty" );
        }

    }

}
