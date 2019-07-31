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

import com.adle.projet.entity.Role;
import com.adle.projet.entity.User;
import com.adle.projet.service.RoleService;
import com.adle.projet.service.UserService;
import com.adle.projet.tools.PasswordEncryptor;
import com.adle.projet.validator.UserLoggValidator;
import com.adle.projet.validator.UserUpdateEmailValidator;
import com.adle.projet.validator.UserUpdateNickNameValidator;
import com.adle.projet.validator.UserUpdatePasswordValidator;
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
    private UserValidator               userValidator;

    @Autowired
    private UserLoggValidator           userLoggValidator;

    @Autowired
    private UserUpdateEmailValidator    userUpdateEmailValidator;

    @Autowired
    private UserUpdateNickNameValidator userUpdateNickNameValidator;

    @Autowired
    private UserUpdatePasswordValidator userUptadePasswordValidator;

    @Autowired
    private UserService                 userService;

    @Autowired
    private RoleService                 roleService;

    /*
     * ***************************** List of User *****************************
     */
    /**
     * Page for list of user
     * 
     * @param theModel
     *            attribute to page jsp
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
     * @param request
     *            information on the session
     * @return user page
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
     *            attribute to page jsp
     * @return update page
     */

    @GetMapping( "/maj" )
    public String showFormForUpdate( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute( "userLoginId" );
        User theUser = userService.getUser( userId );
        theModel.addAttribute( "user", theUser );
        return "user_uptade";
    }

    /**
     * Process after submit button click on user_update page
     * 
     * @param theUser
     *            User on page
     * @param result
     *            for errors on page
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return user page
     */

    @PostMapping( "/updateUser" )
    public String updateUser( @Valid @ModelAttribute( "user" ) User theUser, BindingResult result, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute( "userLoginId" );
        User userUpdate = userService.getUser( userId );
        if ( !theUser.getNickName().equals( userUpdate.getNickName() ) ) {
            userUpdateNickNameValidator.validate( theUser, result );
        }
        if ( !theUser.getEmail().equals( userUpdate.getEmail() ) ) {
            userUpdateEmailValidator.validate( theUser, result );
        }

        if ( !PasswordEncryptor.checkPassword( theUser.getPassword(), userUpdate.getPassword() ) ) {
            userUptadePasswordValidator.validate( theUser, result );
        }

        if ( result.hasErrors() ) {
            theModel.addAttribute( "user", theUser );
            return "user_uptade";
        } else {
            userService.updateUser( theUser );
            session.setAttribute( "userLoginId", theUser.getId() );
            return "redirect:/compte/moncompte";
        }
    }

    /*
     * **************************** User Connexion ****************************
     */
    /**
     * Page to connect user
     * 
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return account login
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

    /**
     * Process after submit button click on account_login page
     * 
     * @param theUser
     *            User on page
     * @param result
     *            for errors on page
     * @param request
     *            information on the session
     * @return user account page
     */
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

    /*
     * **************************** User Page ****************************
     */

    /**
     * User account page
     * 
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return user account page
     */
    @GetMapping( "/moncompte" )
    public String showFormForAccountUser( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute( "userLoginId" );
        User theUser = userService.getUser( userId );
        theModel.addAttribute( "user", theUser );
        return "user_view";
    }

    /*
     * **************************** User Log Out ****************************
     */

    /**
     * Page to log out user
     * 
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            attribute to page jsp
     * @return account login
     */
    @GetMapping( "/deconnexion" )
    public String showFormForDeconnectionUser( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/compte/connexion";
    }
}
