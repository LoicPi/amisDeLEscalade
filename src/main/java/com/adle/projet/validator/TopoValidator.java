package com.adle.projet.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.entity.Topo;

@Component
public class TopoValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( TopoValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return Topo.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {
        Topo topo = (Topo) obj;

        if ( topo.getTopoName().equals( "" ) ) {
            logger.info( "Topo_Name is empty" );
            errors.rejectValue( "topoName", "topoValidator.topoName.empty" );
        }

        if ( topo.getTopoDescriptive().equals( "" ) ) {
            logger.info( "Topo_Descriptive is empty" );
            errors.rejectValue( "topoDescriptive", "topoValidator.topoDescriptive.empty" );
        }

        if ( topo.getTopoCity().equals( "" ) ) {
            logger.info( "Topo_City is empty" );
            errors.rejectValue( "topoCity", "topoValidator.topoCity.empty" );
        }

        if ( topo.getTopoCountry().equals( "" ) ) {
            logger.info( "Topo_Country is empty" );
            errors.rejectValue( "topoCountry", "topoValidator.topoCountry.empty" );
        }

        if ( topo.getTopoReleaseDate() == null ) {
            logger.info( "Topo_ReleaseDate is empty" );
            errors.rejectValue( "topoReleaseDate", "topoValidator.topoReleaseDate.empty" );
        }

        String lowerTopoCountry = topo.getTopoCountry().toLowerCase();

        if ( topo.getTopoCounty() == null && lowerTopoCountry.equals( "france" ) ) {
            logger.info( "Topo_County is empty" );
            errors.rejectValue( "topoCounty", "topoValidator.topoCounty.empty" );
        }

        if ( topo.getTopoCounty() != null && !( lowerTopoCountry.equals( "france" ) ) ) {
            logger.info( "Topo_Country is not correct" );
            errors.rejectValue( "topoCountry", "topoValidator.topoCountry.notcorrect" );
        }
    }

}
