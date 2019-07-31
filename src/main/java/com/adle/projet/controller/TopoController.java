package com.adle.projet.controller;

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
     * ************************* Registration of Topo *************************
     */
    /**
     * Page to view topo registration
     * 
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return
     */
    @GetMapping( "/creationtopo" )
    public String formForTopoCreation( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userLoginId" ) == null ) {
            User theUser = new User();
            theModel.addAttribute( "user", theUser );
            return "account_login";
        } else {
            Integer userId = (Integer) session.getAttribute( "userLoginId" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
            Topo theTopo = new Topo();
            theModel.addAttribute( "topo", theTopo );
            return "registration_topo";
        }
    }

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
