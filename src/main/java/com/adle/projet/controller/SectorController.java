package com.adle.projet.controller;

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

import com.adle.projet.dto.UpdateSector;
import com.adle.projet.entity.Sector;
import com.adle.projet.entity.Spot;
import com.adle.projet.entity.User;
import com.adle.projet.service.SectorService;
import com.adle.projet.service.SpotService;
import com.adle.projet.service.UserService;
import com.adle.projet.validator.SectorUpdateValidator;
import com.adle.projet.validator.SectorValidator;

@Controller
@RequestMapping( "/site/{spotId}/secteur" )
public class SectorController {

    private static final Logger   logger = LogManager.getLogger( SectorController.class );

    @Autowired
    private UserService           userService;

    @Autowired
    private SpotService           spotService;

    @Autowired
    private SectorService         sectorService;

    @Autowired
    private SectorValidator       sectorValidator;

    @Autowired
    private SectorUpdateValidator sectorUpdateValidator;

    /*
     * ************************ Registration of Sector ************************
     */

    /**
     * Page to view sector registration
     * 
     * @param spotId
     *            the id of the spot
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return sector registration page
     * 
     */
    @GetMapping( "/creationsecteur" )
    public String formForSectorCreation( @PathVariable( "spotId" ) Integer spotId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/site/" + spotId + "/vuesite";
        } else {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
            Spot theSpot = spotService.getSpot( spotId );
            theModel.addAttribute( "spot", theSpot );
            Sector theSector = new Sector();
            theModel.addAttribute( "sector", theSector );
            return "sector_registration";
        }
    }

    /**
     * Process after submit button click on sector_registration page
     * 
     * @param spotId
     *            the id of the spot
     * @param theSector
     *            sector create on registration_sector page
     * @param theModel
     *            attribute to page jsp
     * @param result
     *            result of validation form
     * @param request
     *            information on the session
     * @return the sector view
     */
    @PostMapping( "/savesector" )
    public String saveSector( @PathVariable( "spotId" ) Integer spotId, @ModelAttribute( "sector" ) Sector theSector,
            Model theModel, BindingResult result, HttpServletRequest request ) {

        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/site/" + spotId + "/vuesite";
        } else {
            sectorValidator.validate( theSector, result );
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            Spot theSpot = spotService.getSpot( spotId );
            if ( result.hasErrors() ) {
                theModel.addAttribute( "user", theUser );
                theModel.addAttribute( "spot", theSpot );
                theModel.addAttribute( "sector", theSector );
                return "sector_registration";
            } else {
                theSector.setUser( theUser );
                theSector.setSpot( theSpot );
                sectorService.saveSector( theSector );
                logger.info( "The Sector has been saved successfully : " + theSector );
                return "redirect:/site/" + spotId + "/secteur/" + theSector.getId() + "/vuesecteur";
            }
        }
    }

    /*
     * ************************* Sector Page *************************
     */

    /**
     * Page of details on sector
     * 
     * @param spotId
     *            the id of the spot
     * @param sectorId
     *            the id of the sector
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return sector view
     */
    @GetMapping( "/{sectorId}/vuesecteur" )
    public String formForSectorView( @PathVariable( "spotId" ) Integer spotId,
            @PathVariable( "sectorId" ) Integer sectorId, Model theModel,
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
        theModel.addAttribute( "paths", theSector.getPaths() );
        theModel.addAttribute( "sector", theSector );
        logger.info( "The Sector is : " + theSector );
        return "sector_view";
    }

    /*
     * ***************************** Sector Update *****************************
     */
    /**
     * Page to sector
     * 
     * @param spotId
     *            the id of the spot
     * @param sectorId
     *            the id of the sector
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return sector update page
     * 
     */
    @GetMapping( "/{sectorId}/majsecteur" )
    public String showFormForUpdateSector( @PathVariable( "spotId" ) Integer spotId,
            @PathVariable( "sectorId" ) Integer sectorId, Model theModel,
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
            UpdateSector theSector = new UpdateSector();
            Sector sectorToUpdate = sectorService.getSector( sectorId );
            theModel.addAttribute( "sector", sectorToUpdate );
            theSector.setId( sectorId );
            theSector.setUpdateSectorName( sectorToUpdate.getSectorName() );
            theSector.setUpdateSectorDescriptive( sectorToUpdate.getSectorDescriptive() );
            theSector.setUpdateSectorAccess( sectorToUpdate.getSectorAccess() );
            theSector.setSpot( sectorToUpdate.getSpot() );
            theSector.setUser( sectorToUpdate.getUser() );
            if ( !( ( sectorToUpdate.getUser() ).getId().equals( userId ) ) ) {
                return "redirect:/site/" + spotId + "/secteur/" + sectorId +
                        "/vuesecteur";
            } else {
                theModel.addAttribute( "updateSector", theSector );
                return "sector_update";
            }
        }
    }

    /**
     * Process after submit button click on sector_update page
     * 
     * @param spotId
     *            the id of the spot
     * @param sectorId
     *            the id of the sector
     * @param theSector
     *            the sector updated
     * @param result
     *            result of validation form
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the view of the sector updating
     */
    @PostMapping( "/{sectorId}/updatesector" )
    public String updateSector( @PathVariable( "spotId" ) Integer spotId,
            @PathVariable( "sectorId" ) Integer sectorId,
            @Valid @ModelAttribute( "updateSector" ) UpdateSector theSector,
            BindingResult result,
            Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute( "idUser" );
        User theUser = userService.getUser( userId );
        theModel.addAttribute( "user", theUser );
        Spot theSpot = spotService.getSpot( spotId );
        theModel.addAttribute( "spot", theSpot );
        sectorUpdateValidator.validate( theSector, result );
        if ( result.hasErrors() ) {
            Sector sector = sectorService.getSector( sectorId );
            theModel.addAttribute( "sector", sector );
            theModel.addAttribute( "updateSector", theSector );
            return "sector_update";
        } else {
            Sector sectorUpdate = sectorService.getSector( sectorId );
            sectorUpdate.setSectorName( theSector.getUpdateSectorName() );
            sectorUpdate.setSectorDescriptive( theSector.getUpdateSectorDescriptive() );
            sectorService.updateSector( sectorUpdate );
            logger.info( "The Sector has been successfully updated : " + sectorUpdate );
            return "redirect:/site/" + spotId + "/secteur/" + sectorId +
                    "/vuesecteur";
        }
    }

    /*
     * ************************* Sector Delete *************************
     */

    /**
     * Page to delete a sector
     * 
     * @param spotId
     *            the id of the spot
     * @param sectorId
     *            the id of the sector
     * @param request
     *            information on the session
     * @return view of the spot
     */
    @GetMapping( "{sectorId}/deletesector" )
    public String deleteSector( @PathVariable( "spotId" ) Integer spotId, @PathVariable( "sectorId" ) Integer sectorId,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            logger.info( "The sector has been deleted  : " + sectorService.getSector( sectorId ) );
            sectorService.deleteSector( sectorId );
            return "redirect:/site/" + spotId + "/vuesite";
        }
    }

}
