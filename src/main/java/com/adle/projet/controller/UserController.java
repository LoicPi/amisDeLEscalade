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

import com.adle.projet.entity.Role;
import com.adle.projet.entity.User;
import com.adle.projet.service.RoleService;
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

    @Autowired
    private RoleService       roleService;

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
        List<Role> theRoles = roleService.getRoles();
        theModel.addAttribute( "users", theUsers );
        theModel.addAttribute( "roles", theRoles );
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
        List<Role> roles = roleService.getRoles();
        theModel.addAttribute( "user", theUser );
        theModel.addAttribute( "roles", roles );
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
    public String saveUser( @Valid @ModelAttribute( "user" ) User theUser, BindingResult result, Model theModel,
            HttpServletRequest request ) {
        userValidator.validate( theUser, result );
        HttpSession session = request.getSession();

        if ( result.hasErrors() ) {
            theModel.addAttribute( "user", theUser );
            return "registration";
        }
        if ( theUser.getUserMember() ) {
            theUser.setUserRole( roleService.findUserRoleByCode( theUser.getUserMember() ) );
        }
        userService.saveUser( theUser );
        session.setAttribute( "userLoginId", theUser.getId() );
        return "redirect:/compte/moncompte";
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
            Model theModel, HttpServletRequest request ) {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute( "userLoginId" );
        User theUser = userService.getUser( userId );
        theModel.addAttribute( "user", theUser );
        return "user_uptade";
    }

    /**
     * 
     * @param theModel
     * @return
     */
    @GetMapping( "/connexion" )
    public String showFormForLogin( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userLoginId" ) == null ) {
            User theUser = new User();
            theModel.addAttribute( "user", theUser );
            return "account_login";
        } else {
            Integer userId = (Integer) session.getAttribute( "userLoginId" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
            return "redirect:/compte/moncompte";
        }
    }

    @PostMapping( "/logUser" )
    public String logUser( @ModelAttribute( "user" ) User theUser, BindingResult result, HttpServletRequest request ) {
        userLoggValidator.validate( theUser, result );

        HttpSession session = request.getSession();

        if ( result.hasErrors() ) {
            session.setAttribute( "user", theUser );
            return "account_login";
        } else {
            User userLogin = userService.findUserByEmail( theUser.getEmail() ).get( 0 );
            session.setAttribute( "userLoginId", userLogin.getId() );
            return "redirect:/compte/moncompte";
        }
    }

    @GetMapping( "/moncompte" )
    public String showFormForAccountUser( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute( "userLoginId" );
        User theUser = userService.getUser( userId );
        theModel.addAttribute( "user", theUser );
        return "user_view";
    }
}
