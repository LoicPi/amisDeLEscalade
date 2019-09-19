package com.adle.projet.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.dto.UpdateTopo;

@Component
public class TopoUpdateValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( TopoUpdateValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return UpdateTopo.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {
        UpdateTopo topo = (UpdateTopo) obj;

        if ( topo.getUpdateTopoName().equals( "" ) ) {
            logger.info( "UpdateTopo_Name is empty" );
            errors.rejectValue( "updateTopoName", "topoValidator.topoName.empty" );
        }

        if ( topo.getUpdateTopoName().length() > 100 || topo.getUpdateTopoName().length() < 3 ) {
            logger.info( "Length of UpdateTopo_Name is not correct." );
            errors.rejectValue( "updateTopoName", "topo.name.invalid" );
        }

        if ( topo.getUpdateTopoDescriptive().equals( "" ) ) {
            logger.info( "UpdateTopo_Descriptive is empty" );
            errors.rejectValue( "updateTopoDescriptive", "topoValidator.topoDescriptive.empty" );
        }

        if ( topo.getUpdateTopoDescriptive().length() > 600 || topo.getUpdateTopoDescriptive().length() < 10 ) {
            logger.info( "Length of UpdateTopo_Descriptive is not correct." );
            errors.rejectValue( "updateTopoDescriptive", "topo.descriptive.invalid" );
        }

        if ( topo.getUpdateTopoCity().equals( "" ) ) {
            logger.info( "UpdateTopo_City is empty" );
            errors.rejectValue( "updateTopoCity", "topoValidator.topoCity.empty" );
        }

        if ( topo.getUpdateTopoCity().length() > 100 || topo.getUpdateTopoCity().length() < 3 ) {
            logger.info( "Length of UpdateTopo_City is not correct." );
            errors.rejectValue( "updateTopoCity", "topo.city.invalid" );
        }

        if ( topo.getUpdateTopoCountry().equals( "" ) ) {
            logger.info( "UpdateTopo_Country is empty" );
            errors.rejectValue( "updateTopoCountry", "topoValidator.topoCountry.empty" );
        }

        if ( topo.getUpdateTopoCountry().length() > 100 || topo.getUpdateTopoCountry().length() < 3 ) {
            logger.info( "Length of UpdateTopo_Country is not correct." );
            errors.rejectValue( "updateTopoCountry", "topo.country.invalid" );
        }

        if ( topo.getUpdateTopoReleaseDate().equals( "" ) ) {
            logger.info( "UpdateTopo_ReleaseDate is empty" );
            errors.rejectValue( "updateTopoReleaseDate", "topoValidator.topoReleaseDate.empty" );
        }

        if ( topo.getUpdateTopoReleaseDate().length() != 10 ) {
            logger.info( "Length of UpdateTopo_ReleaseDate is not correct." );
            errors.rejectValue( "updateTopoReleaseDate", "topo.releaseDate.invalid" );
        }

        String lowerTopoCountry = topo.getUpdateTopoCountry().toLowerCase();

        if ( topo.getTopoCounty() == null && lowerTopoCountry.equals( "france" ) ) {
            logger.info( "UpdateTopo_County is empty" );
            errors.rejectValue( "topoCounty", "topoValidator.topoCounty.empty" );
        }

        if ( topo.getTopoCounty() != null && !( lowerTopoCountry.equals( "france" ) ) ) {
            logger.info( "UpdateTopo_Country is not correct" );
            errors.rejectValue( "updateTopoCountry", "topoValidator.topoCountry.notcorrect" );
        }
    }

}
