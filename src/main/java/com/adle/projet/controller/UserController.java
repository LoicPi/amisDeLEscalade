package com.adle.projet.controller;

import java.util.List;

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

import com.adle.projet.entity.User;
import com.adle.projet.service.UserService;
import com.adle.projet.validator.UserLoggValidator;
import com.adle.projet.validator.UserValidator;

/**
 * Controller for User
 * 
 * @author Loïc
 *
 */
@Controller
@RequestMapping( "/compte" )
public class UserController {

    @Autowired
    private UserValidator     userValidator;

    @Autowired
    private UserLoggValidator userLoggValidator;

    @Autowired
    private UserService       userService;

    /*
     * ***************************** List of User *****************************
     */
    /**
     * Page for list of user
     * 
     * @param theModel
     * @return list page
     */

    @GetMapping( "/liste" )
    public String listUsers( Model theModel ) {
        List<User> theUsers = userService.getUsers();
        theModel.addAttribute( "users", theUsers );
        return "list_users";
    }

    /*
     * ************************* Registration of User *************************
     */

    /**
     * Page to view a registration of a user
     * 
     * @param theModel
     *            attribute to page jsp
     * @return register page
     */

    @GetMapping( "/inscription" )
    public String showFormForAdd( Model theModel ) {
        User theUser = new User();
        theModel.addAttribute( "user", theUser );
        return "registration";
    }

    /**
     * Process after submit button click on inscription page
     * 
     * @param theUser
     *            user create on inscritption page
     * @param result
     *            result of validation form
     * @param theModel
     *            attribute to page jsp
     * @return list page
     */
    @PostMapping( "/saveUser" )
    public String saveUser( @Valid @ModelAttribute( "user" ) User theUser, BindingResult result, Model theModel ) {
        userValidator.validate( theUser, result );

        if ( result.hasErrors() ) {
            theModel.addAttribute( "user", theUser );
            return "registration";
        }
        userService.saveUser( theUser );
        return "redirect:/compte/liste";
    }

    /*
     * ***************************** User Uptade *****************************
     */
    /**
     * Page to uptade user
     * 
     * @param theId
     *            id of database for user
     * @param theModel
     *            attibute to page jsp
     * @return register page
     */

    @GetMapping( "/maj" )
    public String showFormForUpdate( @RequestParam( "userId" ) int theId,
            Model theModel ) {
        User theUser = userService.getUser( theId );
        theModel.addAttribute( "user", theUser );
        return "registration";
    }

    /**
     * 
     * @param theModel
     * @return
     */
    @GetMapping( "/connexion" )
    public String showFormForLogin( Model theModel ) {
        User theUser = new User();
        theModel.addAttribute( "user", theUser );
        return "logg";
    }

    @PostMapping( "/logUser" )
    public String logUser( @ModelAttribute( "user" ) User theUser, BindingResult result, Model theModel ) {
        userLoggValidator.validate( theUser, result );
        if ( result.hasErrors() ) {
            theModel.addAttribute( "user", theUser );
            return "logg";
        }
        return "redirect:/compte/utilisateur";
    }
}
