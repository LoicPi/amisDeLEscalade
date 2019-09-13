package com.adle.projet.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.dto.UpdateSector;

@Component
public class SectorUpdateValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( SectorUpdateValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return UpdateSector.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {
        UpdateSector updateSector = (UpdateSector) obj;

        if ( updateSector.getSectorName().equals( "" ) ) {
            logger.info( "UpdateSector_Name is empty" );
            errors.rejectValue( "sectorName", "sectorValidator.sectorName.empty" );
        }

        if ( updateSector.getSectorName().length() > 100 || updateSector.getSectorName().length() < 3 ) {
            logger.info( "Length of UpdateSector_Name is not correct." );
            errors.rejectValue( "sectorName", "sector.name.invalid" );
        }

        if ( updateSector.getSectorDescriptive().equals( "" ) ) {
            logger.info( "UpdateSector_Descriptive is empty" );
            errors.rejectValue( "sectorDescriptive", "sectorValidator.sectorDescriptive.empty" );
        }

        if ( updateSector.getSectorDescriptive().length() > 600 || updateSector.getSectorDescriptive().length() < 10 ) {
            logger.info( "Length of UpdateSector_Descriptive is not correct." );
            errors.rejectValue( "sectorDescriptive", "sector.descriptive.invalid" );
        }

        if ( updateSector.getSectorAccess().equals( "" ) ) {
            logger.info( "UpdateSector_Access is empty" );
            errors.rejectValue( "sectorAccess", "sectorValidator.sectorAccess.empty" );
        }

        if ( updateSector.getSectorAccess().length() > 300 || updateSector.getSectorAccess().length() < 10 ) {
            logger.info( "Length of UpdateSector_Access is not correct." );
            errors.rejectValue( "sectorAccess", "sector.access.invalid" );
        }
    }
}
