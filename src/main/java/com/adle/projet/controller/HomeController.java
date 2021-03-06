package com.adle.projet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adle.projet.entity.User;
import com.adle.projet.service.UserService;

@Controller
@RequestMapping( "/" )
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping( "" )
    public String showFormForHome( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) != null ) {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
            return "home";
        } else {
            return "home";
        }
    }
}
