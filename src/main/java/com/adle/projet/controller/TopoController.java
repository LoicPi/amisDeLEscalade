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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adle.projet.dto.UpdateTopo;
import com.adle.projet.entity.Topo;
import com.adle.projet.entity.User;
import com.adle.projet.service.EmailService;
import com.adle.projet.service.TopoService;
import com.adle.projet.service.UserService;
import com.adle.projet.validator.TopoUpdateValidator;
import com.adle.projet.validator.TopoValidator;

/**
 * Controller for Topo
 * 
 * @author Loïc
 *
 */
@Controller
@RequestMapping( "/topo" )
public class TopoController {

    @Autowired
    private UserService         userService;

    @Autowired
    private TopoService         topoService;

    @Autowired
    private TopoValidator       topoValidator;

    @Autowired
    private TopoUpdateValidator topoUpdateValidator;

    @Autowired
    private EmailService        emailService;

    /*
     * ************************* List of Topo *************************
     */

    /**
     * Return the page containing all topos
     * 
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return topos list page
     * 
     */
    @GetMapping( "/" )
    public String listTopos( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userLoginId" ) != null ) {
            Integer userId = (Integer) session.getAttribute( "userLoginId" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
        }
        List<Topo> theTopos = topoService.getTopos();
        theModel.addAttribute( "topos", theTopos );
        return "topo_list";
    }

    /*
     * ************************* Registration of Topo *************************
     */
    /**
     * Page to view topo registration
     * 
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return topo registration page
     */
    @GetMapping( "/creationtopo" )
    public String formForTopoCreation( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userLoginId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "userLoginId" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
            Topo theTopo = new Topo();
            theModel.addAttribute( "topo", theTopo );
            return "topo_registration";
        }
    }

    /**
     * Page to save topos
     * 
     * @param theTopo
     *            topo create on registration_topo page
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the topo view
     */
    @PostMapping( "/saveTopo" )
    public String saveUser( @ModelAttribute( "topo" ) Topo theTopo, Model theModel, BindingResult result,
            HttpServletRequest request ) {

        HttpSession session = request.getSession();
        if ( session.getAttribute( "userLoginId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            topoValidator.validate( theTopo, result );
            Integer userId = (Integer) session.getAttribute( "userLoginId" );
            User theUser = userService.getUser( userId );
            theTopo.setUserId( theUser );
            topoService.saveTopo( theTopo );
            session.setAttribute( "userLoginId", userId );
            session.setAttribute( "topoId", theTopo.getId() );
            return "redirect:/topo/vuetopo?topoId=" + theTopo.getId();
        }
    }

    /*
     * ************************* Topo Page *************************
     */

    /**
     * Page of details on topo
     * 
     * @param topoId
     *            the id of the topo
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return topo view
     */
    @GetMapping( "/vuetopo" )
    public String formForTopoView( @RequestParam( "topoId" ) Integer topoId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userLoginId" ) != null ) {
            Integer userId = (Integer) session.getAttribute( "userLoginId" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
        }
        Topo theTopo = topoService.getTopo( topoId );
        theModel.addAttribute( "topo", theTopo );
        session.setAttribute( "topoId", theTopo.getId() );
        return "topo_view";
    }

    /*
     * ************************* Topo Update *************************
     */

    /**
     * Page to update topo
     * 
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return topo_update view
     */
    @GetMapping( "/majtopo" )
    public String formForTopoUpdate( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userLoginId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "userLoginId" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
            Integer topoId = (Integer) session.getAttribute( "topoId" );
            UpdateTopo theTopo = new UpdateTopo();
            Topo topoToUpdate = topoService.getTopo( topoId );
            theTopo.setId( topoId );
            theTopo.setTopoName( topoToUpdate.getTopoName() );
            theTopo.setTopoCity( topoToUpdate.getTopoCity() );
            theTopo.setTopoCountry( topoToUpdate.getTopoCountry() );
            theTopo.setTopoCounty( topoToUpdate.getTopoCounty() );
            theTopo.setTopoDescriptive( topoToUpdate.getTopoDescriptive() );
            theTopo.setTopoReleaseDate( topoToUpdate.getTopoReleaseDate() );
            theTopo.setUserId( topoToUpdate.getUserId() );
            Integer topoUserId = theTopo.idUser();
            if ( !topoUserId.equals( userId ) ) {
                return "redirect:/";
            } else {
                theModel.addAttribute( "updateTopo", theTopo );
                return "topo_update";
            }
        }
    }

    @PostMapping( "/updatetopo" )
    public String updateTopo( @Valid @ModelAttribute( "updateTopo" ) UpdateTopo theTopo, BindingResult result,
            Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute( "userLoginId" );
        User theUser = userService.getUser( userId );
        theModel.addAttribute( "user", theUser );
        topoUpdateValidator.validate( theTopo, result );
        if ( result.hasErrors() ) {
            theModel.addAttribute( "updateTopo", theTopo );
            return "topo_update";
        } else {
            Topo topoUpdate = topoService.getTopo( theTopo.getId() );
            topoUpdate.setTopoName( theTopo.getTopoName() );
            topoUpdate.setTopoCity( theTopo.getTopoCity() );
            topoUpdate.setTopoCounty( theTopo.getTopoCounty() );
            topoUpdate.setTopoCountry( theTopo.getTopoCountry() );
            topoUpdate.setTopoDescriptive( theTopo.getTopoDescriptive() );
            topoUpdate.setTopoReleaseDate( theTopo.getTopoReleaseDate() );
            topoService.updateTopo( topoUpdate );
            session.setAttribute( "userLoginId", userId );
            session.setAttribute( "topo", topoUpdate );
            return "redirect:/topo/vuetopo?topoId=" + topoUpdate.getId();
        }
    }

    /*
     * ************************* Topo Delete *************************
     */

    /**
     * Page to delete a topo
     * 
     * @param theId
     *            the id of the topo
     * @return view of the topo's list
     */
    @GetMapping( "/deletetopo" )
    public String deleteTopo( @RequestParam( "topoId" ) int theId, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userLoginId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            topoService.deleteTopo( theId );
            return "redirect:/topo/";
        }
    }

    /*
     * ************************* Topo Booking *************************
     */

    /**
     * Page to book a Topo
     * 
     * @param topoId
     *            the id of the topo
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the view of the topo update
     */
    @GetMapping( "/bookingtopo" )
    public String bookTopo( @RequestParam( "topoId" ) Integer topoId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userLoginId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "userLoginId" );
            User userTaker = userService.getUser( userId );
            Topo theTopo = topoService.getTopo( topoId );
            User userLender = theTopo.getUserId();
            theTopo.setTopoAvailability( true );
            topoService.updateTopo( theTopo );

            String mailFrom = userTaker.getEmail();
            String mailTo = userLender.getEmail();
            String mailSubject = "Demande de réservation sur le topo : " + theTopo.getTopoName();
            String mailText = "Bonjour " + userLender.getLastName() +
                    "\n\nVotre topo " + theTopo.getTopoName() + " a fait l'objet d'une réservation par "
                    + userTaker.getNickName() + "." +
                    "\n\nMerci de lui envoyer un message à l'adresse mail suivante " + userTaker.getEmail()
                    + " afin de convenir des modalités de prêt du topo." +
                    "\n\nN'oubliez pas de remettre en 'disponible' le topo lors de son retour ou si finalement le prêt ne se fait pas."
                    +
                    "\n\nCordialement," +
                    "\n\nLes amis de l'escalade";
            emailService.sendMessage( mailFrom, mailTo, mailSubject, mailText );

            return "redirect:/topo/vuetopo?topoId=" + theTopo.getId();
        }
    }

    /**
     * Page to available a Topo
     * 
     * @param topoId
     *            the id of the topo
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the view of the topo update
     */
    @GetMapping( "/availabilitytopo" )
    public String availableTopo( @RequestParam( "topoId" ) Integer topoId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userLoginId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Topo theTopo = topoService.getTopo( topoId );
            theTopo.setTopoAvailability( false );
            topoService.updateTopo( theTopo );
            return "redirect:/topo/vuetopo?topoId=" + theTopo.getId();
        }
    }
}
