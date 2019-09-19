package com.adle.projet.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.dto.UpdateLength;

@Component
public class LengthUpdateValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( LengthUpdateValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return UpdateLength.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {

        UpdateLength updateLength = (UpdateLength) obj;

        if ( updateLength.getUpdateLengthRelay() < 0 ) {
            logger.info( "UpdateLength_Relay can't be negative" );
            errors.rejectValue( "updateLengthRelay", "lengthValidator.lengthRelay.negative" );
        }

        if ( ( updateLength.getUpdateLengthRelay() == 0 ) && ( updateLength.getUpdateLengthSpit() == true ) ) {
            logger.info( "UpdateLength_Relay can't be null" );
            errors.rejectValue( "updateLengthRelay", "lengthValidator.lengthRelay.empty" );
        }

        if ( ( updateLength.getUpdateLengthRelay() > 0 ) && ( updateLength.getUpdateLengthSpit() == false ) ) {
            logger.info( "UpdateLength_Spit can't be false" );
            errors.rejectValue( "updateLengthSpit", "lengthValidator.lengthSpit.false" );
        }

        if ( updateLength.getUpdateLengthHeight() < 0 ) {
            logger.info( "UpdateLength_Heigth can't be negative" );
            errors.rejectValue( "updateLengthHeigth", "lengthValidator.lengthHeigth.negative" );
        }

        if ( updateLength.getUpdateLengthHeight() == 0 ) {
            logger.info( "UpdateLength_Heigth can't be null" );
            errors.rejectValue( "updateLengthHeigth", "lengthValidator.lengthHeigth.null" );
        }

        if ( updateLength.getUpdateLengthListing() == null ) {
            logger.info( "UpdateLength_Listing is empty" );
            errors.rejectValue( "updateLengthListing", "lengthValidator.lengthListing.empty" );
        }
    }

}
