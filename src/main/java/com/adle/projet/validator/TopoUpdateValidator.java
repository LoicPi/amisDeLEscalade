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

        if ( topo.getTopoName().equals( "" ) ) {
            logger.info( "Topo_Name is empty" );
            errors.rejectValue( "topoName", "topoValidator.topoName.empty" );
        }

        if ( topo.getTopoName().length() > 100 || topo.getTopoName().length() < 3 ) {
            logger.info( "Length of Topo_Name is not correct." );
            errors.rejectValue( "topoName", "topo.name.invalid" );
        }

        if ( topo.getTopoDescriptive().equals( "" ) ) {
            logger.info( "Topo_Descriptive is empty" );
            errors.rejectValue( "topoDescriptive", "topoValidator.topoDescriptive.empty" );
        }

        if ( topo.getTopoDescriptive().length() > 600 || topo.getTopoDescriptive().length() < 10 ) {
            logger.info( "Length of Topo_Descriptive is not correct." );
            errors.rejectValue( "topoDescriptive", "topo.descriptive.invalid" );
        }

        if ( topo.getTopoCity().equals( "" ) ) {
            logger.info( "Topo_City is empty" );
            errors.rejectValue( "topoCity", "topoValidator.topoCity.empty" );
        }

        if ( topo.getTopoCity().length() > 100 || topo.getTopoCity().length() < 3 ) {
            logger.info( "Length of Topo_City is not correct." );
            errors.rejectValue( "topoCity", "topo.city.invalid" );
        }

        if ( topo.getTopoCounty() == null ) {
            logger.info( "Topo_County is empty" );
            errors.rejectValue( "topoCounty", "topoValidator.topoCounty.empty" );
        }

        if ( topo.getTopoCountry().equals( "" ) ) {
            logger.info( "Topo_Country is empty" );
            errors.rejectValue( "topoCountry", "topoValidator.topoCountry.empty" );
        }

        if ( topo.getTopoCountry().length() > 100 || topo.getTopoCountry().length() < 3 ) {
            logger.info( "Length of Topo_Country is not correct." );
            errors.rejectValue( "topoCountry", "topo.country.invalid" );
        }

        if ( topo.getTopoReleaseDate().equals( "" ) ) {
            logger.info( "Topo_ReleaseDate is empty" );
            errors.rejectValue( "topoReleaseDate", "topoValidator.topoReleaseDate.empty" );
        }

        if ( topo.getTopoReleaseDate().equals( "" ) ) {
            logger.info( "Topo_ReleaseDate is empty" );
            errors.rejectValue( "topoReleaseDate", "topoValidator.topoReleaseDate.empty" );
        }

        if ( topo.getTopoReleaseDate().length() != 10 ) {
            logger.info( "Length of Topo_ReleaseDate is not correct." );
            errors.rejectValue( "topoReleaseDate", "topo.releaseDate.invalid" );
        }
    }

}
