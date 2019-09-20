package com.adle.projet.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.entity.Length;

@Component
public class LengthValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( LengthValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return Length.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {

        Length length = (Length) obj;

        if ( length.getLengthRelay() < 0 ) {
            logger.info( "Length_Relay can't be negative" );
            errors.rejectValue( "lengthRelay", "lengthValidator.lengthRelay.negative" );
        }

        if ( ( length.getLengthRelay() == 0 ) && ( length.getLengthSpit() == true ) ) {
            logger.info( "Length_Relay can't be null" );
            errors.rejectValue( "lengthRelay", "lengthValidator.lengthRelay.empty" );
        }

        if ( ( length.getLengthRelay() > 0 ) && ( length.getLengthSpit() == false ) ) {
            logger.info( "Length_Spit can't be false" );
            errors.rejectValue( "lengthSpit", "lengthValidator.lengthSpit.false" );
        }

        if ( length.getHeigth() < 0 ) {
            logger.info( "Length_Heigth can't be negative" );
            errors.rejectValue( "lengthHeigth", "lengthValidator.lengthHeigth.negative" );
        }

        if ( length.getLengthListing() == null ) {
            logger.info( "Length_Listing is empty" );
            errors.rejectValue( "lengthListing", "lengthValidator.lengthListing.empty" );
        }
    }

}
