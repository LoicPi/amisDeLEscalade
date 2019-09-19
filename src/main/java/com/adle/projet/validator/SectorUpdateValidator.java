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

        if ( updateSector.getUpdateSectorName().equals( "" ) ) {
            logger.info( "UpdateSector_Name is empty" );
            errors.rejectValue( "updateSectorName", "sectorValidator.sectorName.empty" );
        }

        if ( updateSector.getUpdateSectorName().length() > 100 || updateSector.getUpdateSectorName().length() < 3 ) {
            logger.info( "Length of UpdateSector_Name is not correct." );
            errors.rejectValue( "updateSectorName", "sector.name.invalid" );
        }

        if ( updateSector.getUpdateSectorDescriptive().equals( "" ) ) {
            logger.info( "UpdateSector_Descriptive is empty" );
            errors.rejectValue( "updateSectorDescriptive", "sectorValidator.sectorDescriptive.empty" );
        }

        if ( updateSector.getUpdateSectorDescriptive().length() > 600
                || updateSector.getUpdateSectorDescriptive().length() < 10 ) {
            logger.info( "Length of UpdateSector_Descriptive is not correct." );
            errors.rejectValue( "updateSectorDescriptive", "sector.descriptive.invalid" );
        }

        if ( updateSector.getUpdateSectorAccess().equals( "" ) ) {
            logger.info( "UpdateSector_Access is empty" );
            errors.rejectValue( "updateSectorAccess", "sectorValidator.sectorAccess.empty" );
        }

        if ( updateSector.getUpdateSectorAccess().length() > 300
                || updateSector.getUpdateSectorAccess().length() < 10 ) {
            logger.info( "Length of UpdateSector_Access is not correct." );
            errors.rejectValue( "updateSectorAccess", "sector.access.invalid" );
        }
    }
}
