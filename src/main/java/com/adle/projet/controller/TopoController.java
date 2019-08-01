package com.adle.projet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adle.projet.entity.Topo;
import com.adle.projet.entity.User;
import com.adle.projet.service.TopoService;
import com.adle.projet.service.UserService;

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
    private UserService userService;

    @Autowired
    private TopoService topoService;

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
        List<Topo> theTopos = topoService.getTopos();
        theModel.addAttribute( "topos", theTopos );
        return "list_topos";
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
            return "registration_topo";
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
     * @return the user account page
     */
    @PostMapping( "/saveTopo" )
    public String saveUser( @ModelAttribute( "topo" ) Topo theTopo, Model theModel,
            HttpServletRequest request ) {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute( "userLoginId" );
        User theUser = userService.getUser( userId );
        theTopo.setUserId( theUser );
        topoService.saveTopo( theTopo );
        session.setAttribute( "userLoginId", theUser.getId() );
        return "redirect:/compte/moncompte";
    }

}
