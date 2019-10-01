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

import com.adle.projet.dto.SearchSpot;
import com.adle.projet.dto.UpdateSpot;
import com.adle.projet.entity.County;
import com.adle.projet.entity.Level;
import com.adle.projet.entity.Listing;
import com.adle.projet.entity.Spot;
import com.adle.projet.entity.User;
import com.adle.projet.service.CountyService;
import com.adle.projet.service.LevelService;
import com.adle.projet.service.ListingService;
import com.adle.projet.service.SpotService;
import com.adle.projet.service.UserService;
import com.adle.projet.validator.SpotUpdateValidator;
import com.adle.projet.validator.SpotValidator;

@Controller
@RequestMapping( "/site" )
public class SpotController {

    private static final Logger logger = LogManager.getLogger( SpotController.class );

    @Autowired
    private UserService         userService;

    @Autowired
    private SpotService         spotService;

    @Autowired
    private SpotValidator       spotValidator;

    @Autowired
    private SpotUpdateValidator spotUpdateValidator;

    @Autowired
    private CountyService       countyService;

    @Autowired
    private LevelService        levelService;

    @Autowired
    private ListingService      listingService;

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
        SearchSpot searchSpot = new SearchSpot();
        theModel.addAttribute( "searchSpot", searchSpot );
        List<Level> levels = levelService.getLevels();
        Map<Integer, String> levelName = levelService.getLevelNameOfLevels( levels );
        theModel.addAttribute( "level", levelName );
        List<Listing> listings = listingService.getListings();
        Map<Integer, String> listingName = listingService.getListingNameOfListings( listings );
        theModel.addAttribute( "listing", listingName );
        List<County> countys = countyService.getCountys();
        Map<Integer, String> nameCounty = countyService.getCountyNameOfCountys( countys );
        theModel.addAttribute( "county", nameCounty );
        List<Spot> theSpots = spotService.getSpots();
        theModel.addAttribute( "spots", theSpots );
        return "spot_list";
    }

    @PostMapping( "/searchspot" )
    public String searchSpot( @ModelAttribute( "searchSpot" ) SearchSpot searchSpot, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) != null ) {
            Integer userId = (Integer) session.getAttribute( "userId" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
        }

        List<Level> levels = levelService.getLevels();
        Map<Integer, String> levelName = levelService.getLevelNameOfLevels( levels );
        theModel.addAttribute( "level", levelName );
        List<Listing> listings = listingService.getListings();
        Map<Integer, String> listingName = listingService.getListingNameOfListings( listings );
        theModel.addAttribute( "listing", listingName );
        List<County> countys = countyService.getCountys();
        Map<Integer, String> nameCounty = countyService.getCountyNameOfCountys( countys );
        theModel.addAttribute( "county", nameCounty );
        theModel.addAttribute( "searchSpot", searchSpot );

        List<Spot> theSpots = spotService.searchSpots( searchSpot.getName(), searchSpot.getCity(),
                searchSpot.getCounty(), searchSpot.getSectors(), searchSpot.getListing(), searchSpot.getLevel() );
        logger.info( "spots : " + theSpots );
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
            List<County> countys = countyService.getCountys();
            Map<Integer, String> nameCounty = countyService.getCountyNameOfCountys( countys );
            theModel.addAttribute( "county", nameCounty );
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
    @PostMapping( "/savespot" )
    public String saveSpot( @ModelAttribute( "spot" ) Spot theSpot, Model theModel, BindingResult result,
            HttpServletRequest request ) {

        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "userId" );
            User theUser = userService.getUser( userId );
            spotValidator.validate( theSpot, result );
            if ( result.hasErrors() ) {
                List<County> countys = countyService.getCountys();
                Map<Integer, String> nameCounty = countyService.getCountyNameOfCountys( countys );
                theModel.addAttribute( "user", theUser );
                theModel.addAttribute( "county", nameCounty );
                theModel.addAttribute( "spot", theSpot );
                return "spot_registration";
            } else {
                if ( theSpot.getSpotCounty() == 0 ) {
                    theSpot.setCounty( null );
                } else {
                    County theCounty = countyService.getCounty( theSpot.getSpotCounty() );
                    theSpot.setCounty( theCounty );
                }
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
        Spot theSpot = spotService.getSpot( spotId );
        theModel.addAttribute( "spot", theSpot );
        theModel.addAttribute( "sectors", theSpot.getSectors() );
        theModel.addAttribute( "comments", theSpot.getComments() );
        return "spot_view";
    }

    /*
     * ***************************** Spot Update *****************************
     */
    /**
     * Page to update spot
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
            List<County> countys = countyService.getCountys();
            Map<Integer, String> nameCounty = countyService.getCountyNameOfCountys( countys );
            theModel.addAttribute( "county", nameCounty );
            UpdateSpot theSpot = new UpdateSpot();
            Spot spotToUpdate = spotService.getSpot( spotId );
            theModel.addAttribute( "spot", spotToUpdate );
            theSpot.setId( spotId );
            theSpot.setUpdateSpotName( spotToUpdate.getSpotName() );
            theSpot.setUpdateSpotCity( spotToUpdate.getSpotCity() );
            theSpot.setUpdateSpotCountry( spotToUpdate.getSpotCountry() );
            theSpot.setUpdateSpotDescriptive( spotToUpdate.getSpotDescriptive() );
            theSpot.setUpdateSpotAccess( spotToUpdate.getSpotAccess() );
            theSpot.setUser( spotToUpdate.getUser() );
            theSpot.setCounty( spotToUpdate.getCounty() );
            theSpot.setSpotCounty( spotToUpdate.countyOfSpot() );
            Integer spotUserId = theSpot.spotIdUser();
            if ( !( spotUserId.equals( userId ) ) ) {
                return "redirect:/site/" + spotId + "/vuesite";
            } else {
                theModel.addAttribute( "updateSpot", theSpot );
                return "spot_update";
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
        spotUpdateValidator.validate( theSpot, result );
        if ( result.hasErrors() ) {
            Spot spot = spotService.getSpot( spotId );
            List<County> countys = countyService.getCountys();
            Map<Integer, String> nameCounty = countyService.getCountyNameOfCountys( countys );
            theModel.addAttribute( "county", nameCounty );
            theModel.addAttribute( "user", theUser );
            theModel.addAttribute( "spot", spot );
            theModel.addAttribute( "updateSpot", theSpot );
            return "spot_update";
        } else {
            County theCounty = countyService.getCounty( theSpot.getSpotCounty() );
            Spot spotUpdate = spotService.getSpot( spotId );
            spotUpdate.setSpotName( theSpot.getUpdateSpotName() );
            spotUpdate.setSpotCity( theSpot.getUpdateSpotCity() );
            spotUpdate.setSpotCounty( theSpot.getSpotCounty() );
            spotUpdate.setSpotCountry( theSpot.getUpdateSpotCountry() );
            spotUpdate.setSpotDescriptive( theSpot.getUpdateSpotDescriptive() );
            spotUpdate.setSpotAccess( theSpot.getUpdateSpotAccess() );
            spotUpdate.setCounty( theCounty );
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
     * @param request
     *            information on the session
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

    /*
     * ************************* Spot Tag *************************
     */

    /**
     * Command to tag a spot
     * 
     * @param spotId
     *            the id of the spot
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the view of the spot updating
     */

    @GetMapping( "{spotId}/tagofficialspot" )
    public String tagOfficialSpot( @PathVariable( "spotId" ) Integer spotId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Spot theSpot = spotService.getSpot( spotId );
            theSpot.setSpotTag( true );
            spotService.updateSpot( theSpot );
            ;
            return "redirect:/site/" + spotId + "/vuesite";
        }
    }

    /**
     * Command to tag a spot
     * 
     * @param spotId
     *            the id of the spot
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the view of the spot updating
     */
    @GetMapping( "{spotId}/removeofficialspot" )
    public String removeTagOfficialSpot( @PathVariable( "spotId" ) Integer spotId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Spot theSpot = spotService.getSpot( spotId );
            theSpot.setSpotTag( false );
            spotService.updateSpot( theSpot );
            ;
            return "redirect:/site/" + spotId + "/vuesite";
        }
    }

}
