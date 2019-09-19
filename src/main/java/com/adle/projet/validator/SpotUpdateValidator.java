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

        if ( spot.getUpdateSpotName().equals( "" ) ) {
            logger.info( "UpdateSpot_Name is empty" );
            errors.rejectValue( "updateSpotName", "spotValidator.spotName.empty" );
        }

        if ( spot.getUpdateSpotName().length() > 100 || spot.getUpdateSpotName().length() < 3 ) {
            logger.info( "Length of UpdateSpot_Name is not correct." );
            errors.rejectValue( "updateSpotName", "spot.name.invalid" );
        }

        if ( spot.getUpdateSpotCity().equals( "" ) ) {
            logger.info( "UpdateSpot_City is empty" );
            errors.rejectValue( "updateSpotCity", "spotValidator.spotCity.empty" );
        }

        if ( spot.getUpdateSpotCity().length() > 100 || spot.getUpdateSpotCity().length() < 3 ) {
            logger.info( "Length of UpdateSpot_City is not correct." );
            errors.rejectValue( "updateSpotCity", "spot.city.invalid" );
        }

        if ( spot.getUpdateSpotCountry().equals( "" ) ) {
            logger.info( "UpdateSpot_Country is empty" );
            errors.rejectValue( "updateSpotCountry", "spotValidator.spotCountry.empty" );
        }

        if ( spot.getUpdateSpotCountry().length() > 100 || spot.getUpdateSpotCountry().length() < 3 ) {
            logger.info( "Length of UpdateSpot_Country is not correct." );
            errors.rejectValue( "updateSpotCountry", "spot.country.invalid" );
        }

        if ( spot.getUpdateSpotDescriptive().equals( "" ) ) {
            logger.info( "UpdateSpot_Descriptive is empty" );
            errors.rejectValue( "updateSpotDescriptive", "spotValidator.spotDescriptive.empty" );
        }

        if ( spot.getUpdateSpotDescriptive().length() > 600 || spot.getUpdateSpotDescriptive().length() < 10 ) {
            logger.info( "Length of UpdateSpot_Descriptive is not correct." );
            errors.rejectValue( "updateSpotDescriptive", "spot.descriptive.invalid" );
        }

        if ( spot.getUpdateSpotAccess().equals( "" ) ) {
            logger.info( "UpdateSpot_Access is empty" );
            errors.rejectValue( "updateSpotAccess", "spotValidator.spotAccess.empty" );
        }

        if ( spot.getUpdateSpotAccess().length() > 300 || spot.getUpdateSpotAccess().length() < 10 ) {
            logger.info( "Length of UpdateSpot_Access is not correct." );
            errors.rejectValue( "updateSpotAccess", "spot.access.invalid" );
        }

        String lowerSpotCountry = spot.getUpdateSpotCountry().toLowerCase();

        if ( spot.getSpotCounty() == null && lowerSpotCountry.equals( "france" ) ) {
            logger.info( "Spot_County is empty" );
            errors.rejectValue( "spotCounty", "spotValidator.spotCounty.empty" );
        }

        if ( spot.getSpotCounty() != null && !( lowerSpotCountry.equals( "france" ) ) ) {
            logger.info( "UpdateSpot_Country is not correct" );
            errors.rejectValue( "updateSpotCountry", "spotValidator.spotCountry.notcorrect" );
        }
    }

}
