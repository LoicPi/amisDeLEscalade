package com.adle.projet.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.adle.projet.dto.UpdatePasswordUser;
import com.adle.projet.dto.UpdateUser;
import com.adle.projet.entity.Role;
import com.adle.projet.entity.User;
import com.adle.projet.service.RoleService;
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

    private static final Logger         logger = LogManager.getLogger( UserController.class );

    @Autowired
    private UserValidator               userValidator;

    @Autowired
    private UserLoggValidator           userLoggValidator;

    @Autowired
    private UserUpdateValidator         userUpdateValidator;

    @Autowired
    private UserUpdatePasswordValidator userUpdatePasswordValidator;

    @Autowired
    private UserService                 userService;

    @Autowired
    private RoleService                 roleService;

    private Path                        path;

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
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User user = userService.getUser( userId );
            String email = user.getEmail();
            if ( !( email.contentEquals( "lesamisdelescalade@gmail.com" ) ) ) {
                return "redirect:/";
            } else {
                List<User> users = userService.getUsers();
                theModel.addAttribute( "user", user );
                theModel.addAttribute( "users", users );
                return "user_list";
            }
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
        if ( session.getAttribute( "idUser" ) != null ) {
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
            // get the provided image from the form
            MultipartFile userImage = theUser.getUserImage();
            // get root directory to store the image
            String rootDirectory = request.getSession().getServletContext().getRealPath( "/" );
            // change any provided image type to png
            // path = Paths.get(rootDirectory + "/WEB-INF/resources/images" +
            // product.getProductId() + ".png");
            path = Paths.get( rootDirectory + "resources/uploaded-images/user" + theUser.getId() + ".png" );
            // check whether image exists or not

            if ( userImage != null && !userImage.isEmpty() ) {
                try {
                    // convert the image type to png
                    userImage.transferTo( new File( path.toString() ) );
                    theUser.setImage( true );
                } catch ( IllegalStateException | IOException e ) {
                    // oops! something did not work as expected
                    e.printStackTrace();
                    throw new RuntimeException( "Saving User image was not successful", e );
                }
            } else {
                theUser.setImage( false );
            }
            userService.saveUser( theUser );
            session.setAttribute( "idUser", theUser.getId() );
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
        if ( session.getAttribute( "idUser" ) == null || userId != (Integer) session.getAttribute( "idUser" ) ) {
            return "redirect:/compte/connexion";
        } else {
            User userToUpdate = userService.getUser( userId );
            theModel.addAttribute( "user", userToUpdate );
            UpdateUser theUser = new UpdateUser();
            theUser.setId( userId );
            theUser.setUpdateFirstName( userToUpdate.getFirstName() );
            theUser.setUpdateLastName( userToUpdate.getLastName() );
            theUser.setUpdateNickName( userToUpdate.getNickName() );
            theUser.setUpdateEmail( userToUpdate.getEmail() );
            theUser.setRole( userToUpdate.getRole() );
            theModel.addAttribute( "updateUser", theUser );
            return "user_update";
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
        HttpSession session = request.getSession();
        Integer idSession = (Integer) session.getAttribute( "idUser" );
        if ( session.getAttribute( "idUser" ) == null || userId != idSession ) {
            return "redirect:/compte/connexion";
        } else {

            User userUpdate = userService.getUser( userId );
            userUpdateValidator.validate( theUser, result );
            if ( result.hasErrors() ) {
                User userToUpdate = userService.getUser( userId );
                theUser.setRole( userToUpdate.getRole() );
                theModel.addAttribute( "user", userToUpdate );
                theModel.addAttribute( "updateUser", theUser );
                return "user_update";
            } else {
                userUpdate.setFirstName( theUser.getUpdateFirstName() );
                userUpdate.setLastName( theUser.getUpdateLastName() );
                userUpdate.setNickName( theUser.getUpdateNickName() );
                userUpdate.setEmail( theUser.getUpdateEmail() );
                if ( theUser.getUserMember() == null ) {
                    theUser.setUserMember( false );
                }
                if ( theUser.getUserMember() ) {
                    userUpdate.setRole( roleService.findUserRoleByCode( theUser.getUserMember() ) );
                }

                MultipartFile userImage = theUser.getUpdateUserImage();

                String rootDirectory = request.getSession().getServletContext().getRealPath( "/" );

                path = Paths.get( rootDirectory + "resources/uploaded-images/user/" + userUpdate.getId() + ".png" );

                if ( userImage != null && !userImage.isEmpty() ) {
                    try {
                        userImage.transferTo( new File( path.toString() ) );
                        userUpdate.setImage( true );
                    } catch ( IllegalStateException | IOException e ) {
                        e.printStackTrace();
                        throw new RuntimeException( "Saving User image was not successful", e );
                    }
                } else {
                    if ( Files.exists( path ) ) {
                        userUpdate.setImage( true );
                    } else {
                        userUpdate.setImage( false );
                    }
                }
                userService.updateUser( userUpdate );
                return "redirect:/compte/" + userId + "/moncompte";
            }
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
        if ( session.getAttribute( "idUser" ) != null ) {
            Integer userId = (Integer) session.getAttribute( "idUser" );
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
            session.setAttribute( "idUser", userLogin.getId() );
            if ( theUser.getEmail().contentEquals( "lesamisdelescalade@gmail.com" ) ) {
                return "redirect:/compte/";
            } else {
                return "redirect:/compte/" + userLogin.getId() + "/moncompte";
            }
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
        Integer idSession = (Integer) session.getAttribute( "idUser" );
        if ( session.getAttribute( "idUser" ) == null || userId != idSession ) {
            return "redirect:/";
        } else {
            User theUser = userService.getUser( userId );
            if ( theUser.getEmail().contentEquals( "lesamisdelescalade@gmail.com" ) ) {
                return "redirect:/compte/";
            } else {
                theModel.addAttribute( "topos", theUser.getTopos() );
                theModel.addAttribute( "spots", theUser.getSpots() );
                theModel.addAttribute( "user", theUser );
                return "user_view";
            }
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
        Integer idSession = (Integer) session.getAttribute( "idUser" );
        if ( session.getAttribute( "idUser" ) == null || userId != idSession ) {
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
        Integer idSession = (Integer) session.getAttribute( "idUser" );
        if ( session.getAttribute( "idUser" ) == null || userId != idSession ) {
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
        Integer idSession = (Integer) session.getAttribute( "idUser" );
        if ( session.getAttribute( "idUser" ) == null || userId != idSession ) {
            return "redirect:/compte/connexion";
        } else {
            userUpdatePasswordValidator.validate( theUser, result );
            if ( result.hasErrors() ) {
                User user = userService.getUser( userId );
                theModel.addAttribute( "user", user );
                theModel.addAttribute( "updatePasswordUser", theUser );
                return "user_changepassword";
            } else {
                User userToUpdate = userService.getUser( userId );
                userToUpdate.setPassword( theUser.getNewPassword() );
                userService.updatePasswordUser( userToUpdate );
                return "redirect:/compte/" + userId + "/moncompte";
            }
        }
    }

    /*
     * ************************* Delete User *************************
     */

    /**
     * Page to delete user account
     * 
     * @param userId
     *            the id of the user
     * @param request
     *            information on the session
     * @return the home page
     */
    @GetMapping( "{userId}/deleteuser" )
    public String deleteUser( @PathVariable( "userId" ) Integer userId, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Integer idSession = (Integer) session.getAttribute( "idUser" );
        if ( session.getAttribute( "idUser" ) == null || userId != idSession ) {
            return "redirect:/compte/connexion";
        } else {
            // get root directory to store the image
            String rootDirectory = request.getSession().getServletContext().getRealPath( "/" );

            // change any provided image type to png
            // path = Paths.get(rootDirectory + "/WEB-INF/resources/images" +
            // product.getProductId() + ".png");
            path = Paths
                    .get( rootDirectory + "resources/uploaded-images/user/"
                            + userId + ".png" );

            if ( Files.exists( path ) ) {
                try {
                    Files.delete( path );
                } catch ( IOException e ) {
                    e.printStackTrace();
                    throw new RuntimeException( "Delete User image was not successful", e );
                }
            }
            userService.deleteUser( userId );
            session.invalidate();
        }
        return "redirect:/";
    }

    /*
     * ************************ User Membership Status ************************
     */

    /**
     * Button to delete statut member to user
     * 
     * @param userId
     *            id of the user
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the user list
     */
    @GetMapping( "{userId}/memberuserdelete" )
    public String deleteRoleMemberOfUser( @PathVariable( "userId" ) Integer userId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            User theUser = userService.getUser( userId );
            theUser.setRole( roleService.findUserRoleByCode( false ) );
            userService.updateUser( theUser );
            return "redirect:/compte/";
        }
    }

    /**
     * Button to give statut member to user
     * 
     * @param userId
     *            id of the user
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the user list
     */
    @GetMapping( "{userId}/memberuser" )
    public String putRoleMemberToUser( @PathVariable( "userId" ) Integer userId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            User theUser = userService.getUser( userId );
            theUser.setRole( roleService.findUserRoleByCode( true ) );
            userService.updateUser( theUser );
            return "redirect:/compte/";
        }
    }
}
