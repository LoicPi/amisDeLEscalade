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

import com.adle.projet.dto.UpdatePath;
import com.adle.projet.entity.Path;
import com.adle.projet.entity.Sector;
import com.adle.projet.entity.Spot;
import com.adle.projet.entity.Type;
import com.adle.projet.entity.User;
import com.adle.projet.service.PathService;
import com.adle.projet.service.SectorService;
import com.adle.projet.service.SpotService;
import com.adle.projet.service.TypeService;
import com.adle.projet.service.UserService;
import com.adle.projet.validator.PathUpdateValidator;
import com.adle.projet.validator.PathValidator;

@Controller
@RequestMapping( "/site/{spotId}/secteur/{sectorId}/voie" )
public class PathController {

    private static final Logger logger = LogManager.getLogger( PathController.class );

    @Autowired
    private UserService         userService;

    @Autowired
    private SpotService         spotService;

    @Autowired
    private SectorService       sectorService;

    @Autowired
    private PathService         pathService;

    @Autowired
    private PathValidator       pathValidator;

    @Autowired
    private PathUpdateValidator pathUpdateValidator;

    @Autowired
    private TypeService         typeService;

    /*
     * ************************ Registration of Path ************************
     */

    /**
     * Page to view path registration
     * 
     * @param spotId
     *            the id of the spot
     * @param sectorId
     *            the id of the sector
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return path registration page
     */
    @GetMapping( "/creationvoie" )
    public String formForPathCreation( @PathVariable( "spotId" ) Integer spotId,
            @PathVariable( "sectorId" ) Integer sectorId, Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/site/" + spotId + "/secteur/" + sectorId + "/vuesecteur";
        } else {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
            Spot theSpot = spotService.getSpot( spotId );
            theModel.addAttribute( "spot", theSpot );
            Sector theSector = sectorService.getSector( sectorId );
            theModel.addAttribute( "sector", theSector );
            List<Type> types = typeService.getTypes();
            Map<Integer, String> nameType = typeService.getTypeNameOfTypes( types );
            theModel.addAttribute( "type", nameType );
            Path thePath = new Path();
            theModel.addAttribute( "path", thePath );
            return "path_registration";
        }
    }

    /**
     * Process after submit button click on sector_registration page
     * 
     * @param spotId
     *            the id of the spot
     * @param sectorId
     *            the id of the sector
     * @param thePath
     *            path create on registration path page
     * @param theModel
     *            attribute to page jsp
     * @param result
     *            result of validation form
     * @param request
     *            information on the session
     * @return the path view
     */
    @PostMapping( "/savepath" )
    public String savePath( @PathVariable( "spotId" ) Integer spotId,
            @PathVariable( "sectorId" ) Integer sectorId, @ModelAttribute( "path" ) Path thePath,
            Model theModel, BindingResult result, HttpServletRequest request ) {

        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/site/" + spotId + "/secteur/" + sectorId + "/vuesecteur";
        } else {
            pathValidator.validate( thePath, result );
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            Spot theSpot = spotService.getSpot( spotId );
            Sector theSector = sectorService.getSector( sectorId );
            if ( result.hasErrors() ) {
                List<Type> types = typeService.getTypes();
                Map<Integer, String> nameType = typeService.getTypeNameOfTypes( types );
                theModel.addAttribute( "type", nameType );
                theModel.addAttribute( "user", theUser );
                theModel.addAttribute( "spot", theSpot );
                theModel.addAttribute( "sector", theSector );
                theModel.addAttribute( "path", thePath );
                return "path_registration";
            } else {
                Type theType = typeService.getType( thePath.getPathType() );
                thePath.setType( theType );
                thePath.setUser( theUser );
                thePath.setSector( theSector );
                logger.info( "The Path has been saved successfully : " + thePath );
                pathService.savePath( thePath );
                return "redirect:/site/" + spotId + "/secteur/" + sectorId + "/voie/" + thePath.getId() + "/vuevoie";
            }
        }
    }

    /*
     * ************************* Path Page *************************
     */

    /**
     * Page of details on path
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
     * @return path view
     */
    @GetMapping( "/{pathId}/vuevoie" )
    public String formForPathView( @PathVariable( "spotId" ) Integer spotId,
            @PathVariable( "sectorId" ) Integer sectorId, @PathVariable( "pathId" ) Integer pathId, Model theModel,
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
        theModel.addAttribute( "lengths", thePath.getLengths() );
        theModel.addAttribute( "path", thePath );
        logger.info( "The Path is : " + thePath );
        return "path_view";
    }

    /*
     * ***************************** Path Uptade *****************************
     */

    /**
     * Page to update path
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
     * @return path update page
     */
    @GetMapping( "/{pathId}/majvoie" )
    public String showFormForUpdatePath( @PathVariable( "spotId" ) Integer spotId,
            @PathVariable( "sectorId" ) Integer sectorId, @PathVariable( "pathId" ) Integer pathId, Model theModel,
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
            List<Type> types = typeService.getTypes();
            Map<Integer, String> nameType = typeService.getTypeNameOfTypes( types );
            theModel.addAttribute( "type", nameType );
            UpdatePath thePath = new UpdatePath();
            Path pathToUpdate = pathService.getPath( pathId );
            theModel.addAttribute( "path", pathToUpdate );
            thePath.setId( pathToUpdate.getId() );
            thePath.setUpdatePathName( pathToUpdate.getPathName() );
            thePath.setSector( pathToUpdate.getSector() );
            thePath.setUser( pathToUpdate.getUser() );
            thePath.setType( pathToUpdate.getType() );
            thePath.setPathType( pathToUpdate.typeIdOfPath() );
            if ( !( ( pathToUpdate.getUser() ).getId().equals( userId ) ) ) {
                return "redirect:/site/" + spotId + "/secteur/" + sectorId +
                        "/voie/" + pathId + "/vuevoie";
            } else {
                theModel.addAttribute( "updatePath", thePath );
                return "path_update";
            }
        }
    }

    /**
     * Process after submit button click on path_update page
     * 
     * @param spotId
     *            the id of the spot
     * @param sectorId
     *            the id of the sector
     * @param pathId
     *            the id of the path
     * @param thePath
     *            the path updated
     * @param result
     *            result of validation form
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the view of the path updating
     */
    @PostMapping( "/{pathId}/updatepath" )
    public String updatePath( @PathVariable( "spotId" ) Integer spotId, @PathVariable( "sectorId" ) Integer sectorId,
            @PathVariable( "pathId" ) Integer pathId,
            @Valid @ModelAttribute( "updatePath" ) UpdatePath thePath, BindingResult result,
            Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute( "idUser" );
        User theUser = userService.getUser( userId );
        Spot theSpot = spotService.getSpot( spotId );
        Sector theSector = sectorService.getSector( sectorId );
        pathUpdateValidator.validate( thePath, result );
        if ( result.hasErrors() ) {
            Path path = pathService.getPath( pathId );
            List<Type> types = typeService.getTypes();
            Map<Integer, String> nameType = typeService.getTypeNameOfTypes( types );
            theModel.addAttribute( "user", theUser );
            theModel.addAttribute( "spot", theSpot );
            theModel.addAttribute( "sector", theSector );
            theModel.addAttribute( "type", nameType );
            theModel.addAttribute( "path", path );
            theModel.addAttribute( "updatePath", thePath );
            return "path_update";
        } else {
            Type theType = typeService.getType( thePath.getPathType() );
            Path pathUpdate = pathService.getPath( pathId );
            pathUpdate.setType( theType );
            pathUpdate.setPathName( thePath.getUpdatePathName() );
            pathService.updatePath( pathUpdate );
            logger.info( "The Path has been successfully updated : " + pathUpdate );
            return "redirect:/site/" + spotId + "/secteur/" + sectorId + "/voie/" + pathId + "/vuevoie";
        }
    }

    /*
     * ************************* Path Delete *************************
     */

    /**
     * Page to delete a path
     * 
     * @param spotId
     *            the id of the spot
     * @param sectorId
     *            the id of the sector
     * @param pathId
     *            the id of the path
     * @param request
     *            information on the session
     * @return view of the sector
     */
    @GetMapping( "{pathId}/deletepath" )
    public String deletePath( @PathVariable( "spotId" ) Integer spotId, @PathVariable( "sectorId" ) Integer sectorId,
            @PathVariable( "pathId" ) Integer pathId,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            logger.info( "The path has been deleted  : " + pathService.getPath( pathId ) );
            pathService.deletePath( pathId );
            return "redirect:/site/" + spotId + "/secteur/" + sectorId + "/vuesecteur";
        }
    }

}
