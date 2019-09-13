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

import com.adle.projet.dto.UpdatePasswordUser;
import com.adle.projet.dto.UpdateUser;
import com.adle.projet.entity.Role;
import com.adle.projet.entity.Spot;
import com.adle.projet.entity.Topo;
import com.adle.projet.entity.User;
import com.adle.projet.service.RoleService;
import com.adle.projet.service.SpotService;
import com.adle.projet.service.TopoService;
import com.adle.projet.service.UserService;
import com.adle.projet.validator.UserLoggValidator;
import com.adle.projet.validator.UserUpdatePasswordValidator;
import com.adle.projet.validator.UserUpdateValidator;
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
    private UserUpdateValidator         userUpdateValidator;

    @Autowired
    private UserUpdatePasswordValidator userUptadePasswordValidator;

    @Autowired
    private UserService                 userService;

    @Autowired
    private RoleService                 roleService;

    @Autowired
    private TopoService                 topoService;

    @Autowired
    private SpotService                 spotService;

    /*
     * ***************************** List of User *****************************
     */
    /**
     * Page for list of user
     * 
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return user list page
     */

    @GetMapping( "/" )
    public String listUsers( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute( "userId" );
        User theUser = userService.getUser( userId );
        if ( session.getAttribute( "userId" ) == null ) {
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
     * @param request
     *            information on the session
     * @return register page
     */

    @GetMapping( "/inscription" )
    public String showFormForAdd( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) != null ) {
            return "redirect:/compte/connexion";
        } else {
            User theUser = new User();
            List<Role> roles = roleService.getRoles();
            theModel.addAttribute( "user", theUser );
            theModel.addAttribute( "roles", roles );
            return "user_registration";
        }
    }

    /**
     * Process after submit button click on user_registration page
     * 
     * @param theUser
     *            user create on inscription page
     * @param result
     *            result of validation form
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the user's account page
     */
    @PostMapping( "/saveuser" )
    public String saveUser( @Valid @ModelAttribute( "user" ) User theUser, BindingResult result, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        userValidator.validate( theUser, result );
        if ( result.hasErrors() ) {
            List<Role> roles = roleService.getRoles();
            theModel.addAttribute( "roles", roles );
            theModel.addAttribute( "user", theUser );
            return "user_registration";
        } else {
            theUser.setRole( roleService.findUserRoleByCode( theUser.getUserMember() ) );
            userService.saveUser( theUser );
            session.setAttribute( "userId", theUser.getId() );
            return "redirect:/compte/" + theUser.getId() + "/moncompte";
        }
    }

    /*
     * ***************************** User Uptade *****************************
     */

    /**
     * Page to uptade user
     * 
     * @param userId
     *            the id of the user
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return update page
     */

    @GetMapping( "{userId}/maj" )
    public String showFormForUpdate( @PathVariable( "userId" ) Integer userId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            User userToUpdate = userService.getUser( userId );
            theModel.addAttribute( "user", userToUpdate );
            UpdateUser theUser = new UpdateUser();
            theUser.setId( userId );
            theUser.setFirstName( userToUpdate.getFirstName() );
            theUser.setLastName( userToUpdate.getLastName() );
            theUser.setNickName( userToUpdate.getNickName() );
            theUser.setEmail( userToUpdate.getEmail() );
            theUser.setRole( userToUpdate.getRole() );
            theModel.addAttribute( "updateUser", theUser );
            return "user_uptade";
        }
    }

    /**
     * Process after submit button click on user_update page
     * 
     * @param userId
     *            the id of the user updated
     * @param theUser
     *            the user updated
     * @param result
     *            result of validation form
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the user's account page
     */

    @PostMapping( "{userId}/updateuser" )
    public String updateUser( @PathVariable( "userId" ) Integer userId,
            @Valid @ModelAttribute( "updateUser" ) UpdateUser theUser, BindingResult result, Model theModel,
            HttpServletRequest request ) {
        User userUpdate = userService.getUser( userId );
        userUpdateValidator.validate( theUser, result );
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
                userUpdate.setRole( roleService.findUserRoleByCode( theUser.getUserMember() ) );
            }
            userService.updateUser( userUpdate );
            return "redirect:/compte/" + userId + "/moncompte";
        }
    }

    /*
     * **************************** User Connexion ****************************
     */
    /**
     * Page to login
     * 
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the login page
     */
    @GetMapping( "/connexion" )
    public String showFormForLogin( Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) != null ) {
            Integer userId = (Integer) session.getAttribute( "userId" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
            return "redirect:/compte/" + userId + "/moncompte";
        } else {
            User theUser = new User();
            theModel.addAttribute( "user", theUser );
            return "account_login";
        }
    }

    /**
     * Process after submit button click on account_login page
     * 
     * @param theUser
     *            User on page
     * @param theModel
     *            attribute to page jsp
     * @param result
     *            result of validation form
     * @param request
     *            information on the session
     * @return user account page
     */
    @PostMapping( "/loguser" )
    public String logUser( @ModelAttribute( "user" ) User theUser, Model theModel, BindingResult result,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        userLoggValidator.validate( theUser, result );
        if ( result.hasErrors() ) {
            theModel.addAttribute( "user", theUser );
            return "account_login";
        } else {
            User userLogin = userService.findUserByEmail( theUser.getEmail() ).get( 0 );
            session.setAttribute( "userId", userLogin.getId() );
            return "redirect:/compte/" + userLogin.getId() + "/moncompte";
        }

    }

    /*
     * **************************** User Page ****************************
     */

    /**
     * Page of user's account
     * 
     * @param userId
     *            the id of the user
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the user's account page
     */
    @GetMapping( "{userId}/moncompte" )
    public String showFormForAccountUser( @PathVariable( "userId" ) Integer userId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            User theUser = userService.getUser( userId );
            List<Topo> topos = topoService.findTopoByUserId( userId );
            List<Spot> spots = spotService.findSpotByUserId( userId );
            theModel.addAttribute( "user", theUser );
            theModel.addAttribute( "topos", topos );
            theModel.addAttribute( "spots", spots );
            return "user_view";
        }
    }

    /*
     * **************************** User Log Out ****************************
     */

    /**
     * Page to log out user
     * 
     * @param userId
     *            the id of the user
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the login page
     */
    @GetMapping( "{userId}/deconnexion" )
    public String showFormForDeconnectionUser( @PathVariable( "userId" ) Integer userId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            session.invalidate();
            return "redirect:/compte/connexion";
        }
    }

    /*
     * ************************* User Password Change *************************
     */

    /**
     * Page to change the password
     * 
     * @param userId
     *            the id of the user
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the password change page
     */
    @GetMapping( "{userId}/majmdp" )
    public String showFormForChangePasswordUser( @PathVariable( "userId" ) Integer userId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            User user = userService.getUser( userId );
            theModel.addAttribute( "user", user );
            UpdatePasswordUser theUser = new UpdatePasswordUser();
            theUser.setId( userId );
            theModel.addAttribute( "updatePasswordUser", theUser );
            return "user_changepassword";
        }
    }

    /**
     * Process after submit button click on user_changepassword page
     * 
     * @param userId
     *            the id of the user
     * @param theUser
     *            theupdatepassword user
     * @param result
     *            result of validation form
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the user's account page
     */
    @PostMapping( "{userId}/updatepassword" )
    public String updatePassword( @PathVariable( "userId" ) Integer userId,
            @Valid @ModelAttribute( "updatePasswordUser" ) UpdatePasswordUser theUser,
            BindingResult result, Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "userId" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            userUptadePasswordValidator.validate( theUser, result );
            if ( result.hasErrors() ) {
                User user = userService.getUser( userId );
                theModel.addAttribute( "user", user );
                theModel.addAttribute( "updatePasswordUser", theUser );
                return "user_changepassword";
            } else {
                User userToUpdate = userService.getUser( userId );
                userToUpdate.setPassword( theUser.getNewPassword() );
                userService.updateUser( userToUpdate );
                return "redirect:/compte/" + userId + "/moncompte";
            }
        }

    }
}
