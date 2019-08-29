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

import com.adle.projet.dto.UpdatePasswordUser;
import com.adle.projet.dto.UpdateUser;
import com.adle.projet.entity.Role;
import com.adle.projet.entity.Topo;
import com.adle.projet.entity.User;
import com.adle.projet.service.RoleService;
import com.adle.projet.service.TopoService;
import com.adle.projet.service.UserService;
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

    @Autowired
    private TopoService                 topoService;

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
    public String listUsers( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userLoginId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            List<User> theUsers = userService.getUsers();
            theModel.addAttribute( "users", theUsers );
            return "user_list";
        }
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
        return "user_registration";
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
        HttpSession session = request.getSession();
        userValidator.validate( theUser, result );
        if ( result.hasErrors() ) {
            theModel.addAttribute( "user", theUser );
            return "user_registration";
        }
        theUser.setUserRole( roleService.findUserRoleByCode( theUser.getUserMember() ) );
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
        if ( session.getAttribute( "userLoginId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "userLoginId" );
            User userToUpdate = userService.getUser( userId );
            theModel.addAttribute( "user", userToUpdate );
            UpdateUser theUser = new UpdateUser();
            theUser.setId( userId );
            theUser.setFirstName( userToUpdate.getFirstName() );
            theUser.setLastName( userToUpdate.getLastName() );
            theUser.setNickName( userToUpdate.getNickName() );
            theUser.setEmail( userToUpdate.getEmail() );
            theUser.setUserRole( userToUpdate.getUserRole() );
            theModel.addAttribute( "updateUser", theUser );
            return "user_uptade";
        }
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
    public String updateUser( @Valid @ModelAttribute( "updateUser" ) UpdateUser theUser, BindingResult result,
            Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userLoginId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            User userUpdate = userService.getUser( theUser.getId() );
            if ( !theUser.getNickName().equals( userUpdate.getNickName() ) ) {
                userUpdateNickNameValidator.validate( theUser, result );
            }
            if ( !theUser.getEmail().equals( userUpdate.getEmail() ) ) {
                userUpdateEmailValidator.validate( theUser, result );
            }
            if ( result.hasErrors() ) {
                theModel.addAttribute( "updateUser", theUser );
                return "user_uptade";
            } else {
                userUpdate.setFirstName( theUser.getFirstName() );
                userUpdate.setLastName( theUser.getLastName() );
                userUpdate.setNickName( theUser.getNickName() );
                userUpdate.setEmail( theUser.getEmail() );
                if ( theUser.getUserMember() == null ) {
                    theUser.setUserMember( false );
                }
                if ( theUser.getUserMember() ) {
                    userUpdate.setUserRole( roleService.findUserRoleByCode( theUser.getUserMember() ) );
                }
                userService.updateUser( userUpdate );
                session.setAttribute( "userLoginId", theUser.getId() );
                return "redirect:/compte/moncompte";
            }
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

        HttpSession session = request.getSession();
        userLoggValidator.validate( theUser, result );
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
        if ( session.getAttribute( "userLoginId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "userLoginId" );
            User theUser = userService.getUser( userId );
            List<Topo> topos = topoService.findTopoByUserId( userId );
            theModel.addAttribute( "user", theUser );
            theModel.addAttribute( "topos", topos );
            return "user_view";
        }
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
        if ( session.getAttribute( "userLoginId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            session.invalidate();
            return "redirect:/compte/connexion";
        }
    }

    /*
     * ************************* User Password Change *************************
     */

    @GetMapping( "/majmdp" )
    public String showFormForChangePasswordUser( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userLoginId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "userLoginId" );
            UpdatePasswordUser theUser = new UpdatePasswordUser();
            theUser.setId( userId );
            theModel.addAttribute( "updatePasswordUser", theUser );
            return "user_changepassword";
        }
    }

    @PostMapping( "/updatePassword" )
    public String updatePassword( @Valid @ModelAttribute( "updatePasswordUser" ) UpdatePasswordUser theUser,
            BindingResult result,
            Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userLoginId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            userUptadePasswordValidator.validate( theUser, result );
            if ( result.hasErrors() ) {
                theModel.addAttribute( "updatePasswordUser", theUser );
                return "user_changepassword";
            } else {
                User userToUpdate = userService.getUser( theUser.getId() );
                userToUpdate.setPassword( theUser.getNewPassword() );
                userService.updateUser( userToUpdate );
                session.setAttribute( "userLoginId", userToUpdate.getId() );
                return "redirect:/compte/moncompte";
            }
        }

    }
}
