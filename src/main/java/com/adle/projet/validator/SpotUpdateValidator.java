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
        return UpdateSpot.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {

        UpdateSpot spot = (UpdateSpot) obj;

        if ( spot.getSpotName().equals( "" ) ) {
            logger.info( "UpdateSpot_Name is empty" );
            errors.rejectValue( "spotName", "spotValidator.spotName.empty" );
        }

        if ( spot.getSpotName().length() > 100 || spot.getSpotName().length() < 3 ) {
            logger.info( "Length of UpdateSpot_Name is not correct." );
            errors.rejectValue( "spotName", "spot.name.invalid" );
        }

        if ( spot.getSpotCity().equals( "" ) ) {
            logger.info( "UpdateSpot_City is empty" );
            errors.rejectValue( "spotCity", "spotValidator.spotCity.empty" );
        }

        if ( spot.getSpotCity().length() > 100 || spot.getSpotCity().length() < 3 ) {
            logger.info( "Length of UpdateSpot_City is not correct." );
            errors.rejectValue( "spotCity", "spot.city.invalid" );
        }

        if ( spot.getSpotCounty() == null ) {
            logger.info( "UpdateSpot_County is empty" );
            errors.rejectValue( "spotCounty", "spotValidator.spotCounty.empty" );
        }

        if ( spot.getSpotCountry().equals( "" ) ) {
            logger.info( "UpdateSpot_Country is empty" );
            errors.rejectValue( "spotCountry", "spotValidator.spotCountry.empty" );
        }

        if ( spot.getSpotCountry().length() > 100 || spot.getSpotCountry().length() < 3 ) {
            logger.info( "Length of UpdateSpot_Country is not correct." );
            errors.rejectValue( "spotCountry", "spot.country.invalid" );
        }

        if ( spot.getSpotDescriptive().equals( "" ) ) {
            logger.info( "UpdateSpot_Descriptive is empty" );
            errors.rejectValue( "spotDescriptive", "spotValidator.spotDescriptive.empty" );
        }

        if ( spot.getSpotDescriptive().length() > 600 || spot.getSpotDescriptive().length() < 10 ) {
            logger.info( "Length of UpdateSpot_Descriptive is not correct." );
            errors.rejectValue( "spotDescriptive", "spot.descriptive.invalid" );
        }

        if ( spot.getSpotAccess().equals( "" ) ) {
            logger.info( "UpdateSpot_Access is empty" );
            errors.rejectValue( "spotAccess", "spotValidator.spotAccess.empty" );
        }

        if ( spot.getSpotAccess().length() > 300 || spot.getSpotAccess().length() < 10 ) {
            logger.info( "Length of UpdateSpot_Access is not correct." );
            errors.rejectValue( "spotAccess", "spot.access.invalid" );
        }
    }

}
