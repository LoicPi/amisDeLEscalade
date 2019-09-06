package com.adle.projet.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.entity.Spot;

@Component
public class SpotValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( SpotValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return Spot.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {

        Spot spot = (Spot) obj;

        if ( spot.getSpotName().equals( "" ) ) {
            logger.info( "Spot_Name is empty" );
            errors.rejectValue( "spotName", "spotValidator.spotName.empty" );
        }

        if ( spot.getSpotCity().equals( "" ) ) {
            logger.info( "Spot_City is empty" );
            errors.rejectValue( "spotCity", "spotValidator.spotCity.empty" );
        }

        if ( spot.getSpotCounty() == null ) {
            logger.info( "Spot_County is empty" );
            errors.rejectValue( "spotCounty", "spotValidator.spotCounty.empty" );
        }

        if ( spot.getSpotCountry() == null ) {
            logger.info( "Spot_Country is empty" );
            errors.rejectValue( "spotCountry", "spotValidator.spotCountry.empty" );
        }

        if ( spot.getSpotDescriptive().equals( "" ) ) {
            logger.info( "Spot_Descriptive is empty" );
            errors.rejectValue( "spotDescriptive", "spotValidator.spotDescriptive.empty" );
        }

        if ( spot.getSpotAccess().equals( "" ) ) {
            logger.info( "Spot_Access is empty" );
            errors.rejectValue( "spotAccess", "spotValidator.spotAccess.empty" );
        }
    }

}
