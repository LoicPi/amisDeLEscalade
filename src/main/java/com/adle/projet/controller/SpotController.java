package com.adle.projet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adle.projet.dto.UpdateSpot;
import com.adle.projet.entity.Sector;
import com.adle.projet.entity.Spot;
import com.adle.projet.entity.User;
import com.adle.projet.service.SectorService;
import com.adle.projet.service.SpotService;
import com.adle.projet.service.UserService;
import com.adle.projet.validator.SpotUpdateValidator;
import com.adle.projet.validator.SpotValidator;

@Controller
@RequestMapping( "/site" )
public class SpotController {

    @Autowired
    private UserService         userService;

    @Autowired
    private SpotService         spotService;

    @Autowired
    private SectorService       sectorService;

    @Autowired
    private SpotValidator       spotValidator;

    @Autowired
    private SpotUpdateValidator spotUpdateValidator;

    /*
     * ************************* List of Spot *************************
     */

    /**
     * Return the page containing all spots
     * 
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return spots list page
     * 
     */
    @GetMapping( "/" )
    public String listSpots( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) != null ) {
            Integer userId = (Integer) session.getAttribute( "userId" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
        }
        List<Spot> theSpots = spotService.getSpots();
        theModel.addAttribute( "spots", theSpots );
        return "spot_list";
    }

    /*
     * ************************* Registration of Spot *************************
     */
    /**
     * Page to view spot registration
     * 
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return spot registration page
     */
    @GetMapping( "/creationsite" )
    public String formForSpotCreation( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "userId" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
            Spot theSpot = new Spot();
            theModel.addAttribute( "spot", theSpot );
            return "spot_registration";
        }
    }

    /**
     * Process after submit button click on spot_registration page
     * 
     * @param theSpot
     *            spot create on registration_spot page
     * @param theModel
     *            attribute to page jsp
     * @param result
     *            result of validation form
     * @param request
     *            information on the session
     * @return the spot view
     */
    @PostMapping( "/saveSpot" )
    public String saveSpot( @ModelAttribute( "spot" ) Spot theSpot, Model theModel, BindingResult result,
            HttpServletRequest request ) {

        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            spotValidator.validate( theSpot, result );
            Integer userId = (Integer) session.getAttribute( "userId" );
            User theUser = userService.getUser( userId );
            if ( result.hasErrors() ) {
                theModel.addAttribute( "user", theUser );
                theModel.addAttribute( "spot", theSpot );
                return "spot_registration";
            } else {
                theSpot.setUser( theUser );
                spotService.saveSpot( theSpot );
                return "redirect:/site/" + theSpot.getId() + "/vuesite";
            }
        }
    }

    /*
     * ************************* Spot Page *************************
     */

    /**
     * Page of details on spot
     * 
     * @param spotId
     *            the id of the spot
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return spot view
     */
    @GetMapping( "/{spotId}/vuesite" )
    public String formForSpotView( @PathVariable( "spotId" ) Integer spotId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) != null ) {
            Integer userId = (Integer) session.getAttribute( "userId" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
        }
        List<Sector> sectors = sectorService.findSectorBySpotId( spotId );
        Spot theSpot = spotService.getSpot( spotId );
        theModel.addAttribute( "spot", theSpot );
        theModel.addAttribute( "sectors", sectors );
        return "spot_view";
    }

    /*
     * ***************************** Spot Uptade *****************************
     */
    /**
     * Page to uptade spot
     * 
     * @param spotId
     *            id of the spot
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return spot update view
     */

    @GetMapping( "/{spotId}/majsite" )
    public String showFormForUpdateSpot( @PathVariable( "spotId" ) Integer spotId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "userId" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
            UpdateSpot theSpot = new UpdateSpot();
            Spot spotToUpdate = spotService.getSpot( spotId );
            theModel.addAttribute( "spot", spotToUpdate );
            theSpot.setId( spotId );
            theSpot.setSpotName( spotToUpdate.getSpotName() );
            theSpot.setSpotCity( spotToUpdate.getSpotCity() );
            theSpot.setSpotCounty( spotToUpdate.getSpotCounty() );
            theSpot.setSpotCountry( spotToUpdate.getSpotCountry() );
            theSpot.setSpotDescriptive( spotToUpdate.getSpotDescriptive() );
            theSpot.setSpotAccess( spotToUpdate.getSpotAccess() );
            theSpot.setUser( spotToUpdate.getUser() );
            Integer spotUserId = theSpot.spotIdUser();
            if ( !( spotUserId.equals( userId ) ) ) {
                return "redirect:/site/" + spotId + "/vuesite";
            } else {
                theModel.addAttribute( "updateSpot", theSpot );
                return "spot_uptade";
            }
        }
    }

    /**
     * Process after submit button click on spot_update page
     * 
     * @param spotId
     *            the id of the spot
     * @param theSpot
     *            the spot updated
     * @param result
     *            result of validation form
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the view of the spot updating
     */
    @PostMapping( "/{spotId}/updatespot" )
    public String updateSpot( @PathVariable( "spotId" ) Integer spotId,
            @Valid @ModelAttribute( "updateSpot" ) UpdateSpot theSpot, BindingResult result,
            Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute( "userId" );
        User theUser = userService.getUser( userId );
        theModel.addAttribute( "user", theUser );
        spotUpdateValidator.validate( theSpot, result );
        if ( result.hasErrors() ) {
            Spot spot = spotService.getSpot( spotId );
            theModel.addAttribute( "spot", spot );
            theModel.addAttribute( "updateSpot", theSpot );
            return "spot_update";
        } else {
            Spot spotUpdate = spotService.getSpot( spotId );
            spotUpdate.setSpotName( theSpot.getSpotName() );
            spotUpdate.setSpotCity( theSpot.getSpotCity() );
            spotUpdate.setSpotCounty( theSpot.getSpotCounty() );
            spotUpdate.setSpotCountry( theSpot.getSpotCountry() );
            spotUpdate.setSpotDescriptive( theSpot.getSpotDescriptive() );
            spotUpdate.setSpotAccess( theSpot.getSpotAccess() );
            spotService.updateSpot( spotUpdate );
            return "redirect:/site/" + spotId + "/vuesite";
        }
    }

    /*
     * ************************* Spot Delete *************************
     */

    /**
     * Page to delete a spot
     * 
     * @param spotId
     *            the id of the spot
     * @return view of the spot's list
     */
    @GetMapping( "{spotId}/deletespot" )
    public String deleteSpot( @PathVariable( "spotId" ) Integer spotId, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            spotService.deleteSpot( spotId );
            return "redirect:/site/";
        }
    }

}
