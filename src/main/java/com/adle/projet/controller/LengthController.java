package com.adle.projet.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adle.projet.dto.UpdateLength;
import com.adle.projet.entity.Length;
import com.adle.projet.entity.Listing;
import com.adle.projet.entity.Path;
import com.adle.projet.entity.Sector;
import com.adle.projet.entity.Spot;
import com.adle.projet.entity.User;
import com.adle.projet.service.LengthService;
import com.adle.projet.service.ListingService;
import com.adle.projet.service.PathService;
import com.adle.projet.service.SectorService;
import com.adle.projet.service.SpotService;
import com.adle.projet.service.UserService;
import com.adle.projet.validator.LengthUpdateValidator;
import com.adle.projet.validator.LengthValidator;

@Controller
@RequestMapping( "/site/{spotId}/secteur/{sectorId}/voie/{pathId}/longueur" )
public class LengthController {

    private static final Logger   logger = LogManager.getLogger( LengthController.class );

    @Autowired
    private UserService           userService;

    @Autowired
    private SpotService           spotService;

    @Autowired
    private SectorService         sectorService;

    @Autowired
    private PathService           pathService;

    @Autowired
    private LengthService         lengthService;

    @Autowired
    private ListingService        listingService;

    @Autowired
    private LengthValidator       lengthValidator;

    @Autowired
    private LengthUpdateValidator lengthUpdateValidator;

    /*
     * ************************ Registration of Path ************************
     */
    /**
     * Page to view length registration
     * 
     * @param spotId
     *            the id of the spot
     * @param sectorId
     *            the id of the sector
     * @param pathId
     *            the id of the path
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return length registration page
     */
    @GetMapping( "/creationlongueur" )
    public String formForLengthCreation( @PathVariable( "spotId" ) Integer spotId,
            @PathVariable( "sectorId" ) Integer sectorId, @PathVariable( "pathId" ) Integer pathId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/site/" + spotId + "/secteur/" + sectorId + "/voie/" + pathId + "/vuevoie";
        } else {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
            Spot theSpot = spotService.getSpot( spotId );
            theModel.addAttribute( "spot", theSpot );
            Sector theSector = sectorService.getSector( sectorId );
            theModel.addAttribute( "sector", theSector );
            Path thePath = pathService.getPath( pathId );
            theModel.addAttribute( "path", thePath );
            List<Listing> listings = listingService.getListings();
            Map<Integer, String> listingName = listingService.getListingNameOfListings( listings );
            theModel.addAttribute( "listing", listingName );
            Length theLength = new Length();
            theModel.addAttribute( "length", theLength );
            return "length_registration";
        }
    }

    /**
     * Process after submit button click on sector_registration page
     * 
     * @param spotId
     *            the id of the spot
     * @param sectorId
     *            the id of the sector
     * @param pathId
     *            the id of the path
     * @param theLength
     *            length create on registration length page
     * @param theModel
     *            attribute to page jsp
     * @param result
     *            result of validation form
     * @param request
     *            information on the session
     * @return the length view
     */
    @PostMapping( "/savelength" )
    public String saveLength( @PathVariable( "spotId" ) Integer spotId,
            @PathVariable( "sectorId" ) Integer sectorId, @PathVariable( "pathId" ) Integer pathId,
            @ModelAttribute( "length" ) Length theLength,
            Model theModel, BindingResult result, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/site/" + spotId + "/secteur/" + sectorId + "/voie" + pathId + "/vuevoie";
        } else {
            lengthValidator.validate( theLength, result );
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            Spot theSpot = spotService.getSpot( spotId );
            Sector theSector = sectorService.getSector( sectorId );
            Path thePath = pathService.getPath( pathId );
            if ( result.hasErrors() ) {
                List<Listing> listings = listingService.getListings();
                Map<Integer, String> listingName = listingService.getListingNameOfListings( listings );
                theModel.addAttribute( "user", theUser );
                theModel.addAttribute( "spot", theSpot );
                theModel.addAttribute( "sector", theSector );
                theModel.addAttribute( "path", thePath );
                theModel.addAttribute( "listing", listingName );
                theModel.addAttribute( "length", theLength );
                return "length_registration";
            } else {
                Listing theListing = listingService.getListing( theLength.getLengthListing() );
                theLength.setPath( thePath );
                theLength.setUser( theUser );
                theLength.setListing( theListing );
                lengthService.saveLength( theLength );
                logger.info( "The Length has been saved successfully : " + theLength );
                return "redirect:/site/" + spotId + "/secteur/" + sectorId + "/voie/" + pathId + "/longueur/"
                        + theLength.getId() + "/vuelongueur";
            }
        }
    }

    /*
     * ************************* Length Page *************************
     */

    /**
     * Page of details on length
     * 
     * @param spotId
     *            the id of the spot
     * @param sectorId
     *            the id of the sector
     * @param pathId
     *            the id of the path
     * @param lengthId
     *            the id of the length
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return length view
     */
    @GetMapping( "/{lengthId}/vuelongueur" )
    public String formForLengthView( @PathVariable( "spotId" ) Integer spotId,
            @PathVariable( "sectorId" ) Integer sectorId, @PathVariable( "pathId" ) Integer pathId,
            @PathVariable( "lengthId" ) Integer lengthId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) != null ) {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
        }
        Spot theSpot = spotService.getSpot( spotId );
        theModel.addAttribute( "spot", theSpot );
        Sector theSector = sectorService.getSector( sectorId );
        theModel.addAttribute( "sector", theSector );
        Path thePath = pathService.getPath( pathId );
        theModel.addAttribute( "path", thePath );
        Length theLength = lengthService.getLength( lengthId );
        theModel.addAttribute( "length", theLength );
        logger.info( "The Length is : " + theLength );
        return "length_view";
    }

    /*
     * ***************************** Length Uptade *****************************
     */

    /**
     * Page to update length
     * 
     * @param spotId
     *            the id of the spot
     * @param sectorId
     *            the id of the sector
     * @param pathId
     *            the id of the path
     * @param lengthId
     *            the id of the length
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return length update page
     */
    @GetMapping( "/{lengthId}/majlongueur" )
    public String showFormForUpdateLength( @PathVariable( "spotId" ) Integer spotId,
            @PathVariable( "sectorId" ) Integer sectorId, @PathVariable( "pathId" ) Integer pathId,
            @PathVariable( "lengthId" ) Integer lengthId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
            Spot theSpot = spotService.getSpot( spotId );
            theModel.addAttribute( "spot", theSpot );
            Sector theSector = sectorService.getSector( sectorId );
            theModel.addAttribute( "sector", theSector );
            Path thePath = pathService.getPath( pathId );
            theModel.addAttribute( "path", thePath );
            List<Listing> listings = listingService.getListings();
            Map<Integer, String> listingName = listingService.getListingNameOfListings( listings );
            theModel.addAttribute( "listing", listingName );
            UpdateLength theLength = new UpdateLength();
            Length lengthToUpdate = lengthService.getLength( lengthId );
            theModel.addAttribute( "length", lengthToUpdate );
            theLength.setId( lengthToUpdate.getId() );
            theLength.setUpdateLengthHeight( lengthToUpdate.getHeigth() );
            theLength.setUpdateLengthRelay( lengthToUpdate.getLengthRelay() );
            theLength.setUpdateLengthSpit( lengthToUpdate.getLengthSpit() );
            theLength.setUpdateLengthListing( lengthToUpdate.getListing().getId() );
            if ( !( ( lengthToUpdate.getUser() ).getId().equals( userId ) ) ) {
                return "redirect:/site/" + spotId + "/secteur/" + sectorId +
                        "/voie/" + pathId + "/longueur/" + lengthId + "/vuelongueur";
            } else {
                theModel.addAttribute( "updateLength", theLength );
                return "length_update";
            }
        }
    }

    /**
     * Process after submit button click on length_update page
     * 
     * @param spotId
     *            the id of the spot
     * @param sectorId
     *            the id of the sector
     * @param pathId
     *            the id of the path
     * @param lengthId
     *            the id of the length
     * @param theLength
     *            the length updated
     * @param result
     *            result of validation form
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the view of the length updating
     */
    @PostMapping( "/{lengthId}/updatelength" )
    public String updateLength( @PathVariable( "spotId" ) Integer spotId, @PathVariable( "sectorId" ) Integer sectorId,
            @PathVariable( "pathId" ) Integer pathId, @PathVariable( "lengthId" ) Integer lengthId,
            @Valid @ModelAttribute( "updateLength" ) UpdateLength theLength, BindingResult result,
            Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute( "idUser" );
        User theUser = userService.getUser( userId );
        Spot theSpot = spotService.getSpot( spotId );
        Sector theSector = sectorService.getSector( sectorId );
        Path thePath = pathService.getPath( pathId );
        lengthUpdateValidator.validate( theLength, result );
        if ( result.hasErrors() ) {
            List<Listing> listings = listingService.getListings();
            Map<Integer, String> listingName = listingService.getListingNameOfListings( listings );
            Length length = lengthService.getLength( lengthId );
            theModel.addAttribute( "listing", listingName );
            theModel.addAttribute( "user", theUser );
            theModel.addAttribute( "spot", theSpot );
            theModel.addAttribute( "sector", theSector );
            theModel.addAttribute( "path", thePath );
            theModel.addAttribute( "length", length );
            theModel.addAttribute( "updateLength", theLength );
            return "length_update";
        } else {
            Listing theListing = listingService.getListing( theLength.getUpdateLengthListing() );
            Length lengthUpdate = lengthService.getLength( lengthId );
            lengthUpdate.setHeigth( theLength.getUpdateLengthHeight() );
            lengthUpdate.setLengthRelay( theLength.getUpdateLengthRelay() );
            if ( lengthUpdate.getLengthRelay() > 0 ) {
                lengthUpdate.setLengthSpit( true );
            } else {
                lengthUpdate.setLengthSpit( false );
            }
            lengthUpdate.setListing( theListing );
            lengthService.updateLength( lengthUpdate );
            logger.info( "The Length has been successfully updated : " + lengthUpdate );
            return "redirect:/site/" + spotId + "/secteur/" + sectorId + "/voie/" + pathId + "/longueur/" + lengthId
                    + "/vuelongueur";
        }
    }

    /*
     * ************************* Length Delete *************************
     */

    /**
     * Page to delete a length
     * 
     * @param spotId
     *            the id of the spot
     * @param sectorId
     *            the id of the sector
     * @param pathId
     *            the id of the path
     * @param lengthId
     *            the id of the length
     * @param request
     *            information on the session
     * @return view of the path
     */
    @GetMapping( "{lengthId}/deletelength" )
    public String deleteLength( @PathVariable( "spotId" ) Integer spotId, @PathVariable( "sectorId" ) Integer sectorId,
            @PathVariable( "pathId" ) Integer pathId, @PathVariable( "lengthId" ) Integer lengthId,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            logger.info( "The length has been deleted  : " + lengthService.getLength( lengthId ) );
            lengthService.deleteLength( lengthId );
            return "redirect:/site/" + spotId + "/secteur/" + sectorId + "/voie/" + pathId + "/vuevoie";
        }
    }

}
