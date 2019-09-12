package com.adle.projet.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.entity.Sector;

@Component
public class SectorValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( SectorValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return Sector.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {

        Sector sector = (Sector) obj;

        if ( sector.getSectorName().equals( "" ) ) {
            logger.info( "Sector_Name is empty" );
            errors.rejectValue( "sectorName", "sectorValidator.sectorName.empty" );
        }

        if ( sector.getSectorDescriptive().equals( "" ) ) {
            logger.info( "Sector_Descriptive is empty" );
            errors.rejectValue( "sectorDescriptive", "sectorValidator.sectorDescriptive.empty" );
        }

        if ( sector.getSectorAccess().equals( "" ) ) {
            logger.info( "Sector_Access is empty" );
            errors.rejectValue( "sectorAccess", "sectorValidator.sectorAccess.empty" );
        }
    }

}
