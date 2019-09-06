package com.adle.projet.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.dto.UpdateSpot;

@Component
public class SpotUpdateValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( SpotUpdateValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return SpotUpdateValidator.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {

        UpdateSpot spot = (UpdateSpot) obj;

        if ( spot.getSpotName().equals( "" ) ) {
            logger.info( "UpdateSpot_Name is empty" );
            errors.rejectValue( "spotName", "spotValidator.spotName.empty" );
        }

        if ( spot.getSpotCity().equals( "" ) ) {
            logger.info( "UpdateSpot_City is empty" );
            errors.rejectValue( "spotCity", "spotValidator.spotCity.empty" );
        }

        if ( spot.getSpotCounty() == null ) {
            logger.info( "UpdateSpot_County is empty" );
            errors.rejectValue( "spotCounty", "spotValidator.spotCounty.empty" );
        }

        if ( spot.getSpotCountry() == null ) {
            logger.info( "UpdateSpot_Country is empty" );
            errors.rejectValue( "spotCountry", "spotValidator.spotCountry.empty" );
        }

        if ( spot.getSpotDescriptive().equals( "" ) ) {
            logger.info( "UpdateSpot_Descriptive is empty" );
            errors.rejectValue( "spotDescriptive", "spotValidator.spotDescriptive.empty" );
        }

        if ( spot.getSpotAccess().equals( "" ) ) {
            logger.info( "UpdateSpot_Access is empty" );
            errors.rejectValue( "spotAccess", "spotValidator.spotAccess.empty" );
        }
    }

}
