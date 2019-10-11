package com.adle.projet.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

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

import com.adle.projet.dto.UpdateTopo;
import com.adle.projet.entity.County;
import com.adle.projet.entity.Topo;
import com.adle.projet.entity.User;
import com.adle.projet.service.CountyService;
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

    private static final Logger logger = LogManager.getLogger( TopoController.class );

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

    @Autowired
    private CountyService       countyService;

    private Path                path1;

    private Path                path2;

    private Path                path3;

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
        if ( session.getAttribute( "idUser" ) != null ) {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
        }
        List<Topo> theTopos = topoService.getTopos();
        theModel.addAttribute( "topos", theTopos );
        logger.info( "List of topos : " + theTopos );
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
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
            List<County> countys = countyService.getCountys();
            Map<Integer, String> nameCounty = countyService.getCountyNameOfCountys( countys );
            theModel.addAttribute( "county", nameCounty );
            Topo theTopo = new Topo();
            theModel.addAttribute( "topo", theTopo );
            return "topo_registration";
        }
    }

    /**
     * Process after submit button click on topo_registration page
     * 
     * @param theTopo
     *            topo create on registration_topo page
     * @param theModel
     *            attribute to page jsp
     * @param result
     *            result of validation form
     * @param request
     *            information on the session
     * @return the topo view
     */
    @PostMapping( "/savetopo" )
    public String saveTopo( @ModelAttribute( "topo" ) Topo theTopo, Model theModel, BindingResult result,
            HttpServletRequest request ) {

        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            topoValidator.validate( theTopo, result );
            if ( result.hasErrors() ) {
                List<County> countys = countyService.getCountys();
                Map<Integer, String> nameCounty = countyService.getCountyNameOfCountys( countys );
                theModel.addAttribute( "user", theUser );
                theModel.addAttribute( "county", nameCounty );
                theModel.addAttribute( "topo", theTopo );
                return "topo_registration";
            } else {
                if ( theTopo.getTopoCounty() == null ) {
                    County theCounty = countyService.getCounty( 102 );
                    theTopo.setCounty( theCounty );
                } else {
                    County theCounty = countyService.getCounty( theTopo.getTopoCounty() );
                    theTopo.setCounty( theCounty );
                }
                theTopo.setUser( theUser );

                topoService.saveTopo( theTopo );

                MultipartFile topoImage1 = theTopo.getTopoImage1();
                MultipartFile topoImage2 = theTopo.getTopoImage2();
                MultipartFile topoImage3 = theTopo.getTopoImage3();

                String rootDirectory = request.getSession().getServletContext().getRealPath( "/" );

                path1 = Paths.get( rootDirectory + "resources/uploaded-images/topo/" + theTopo.getId() + "1.png" );

                path2 = Paths.get( rootDirectory + "resources/uploaded-images/topo/" + theTopo.getId() + "2.png" );

                path3 = Paths.get( rootDirectory + "resources/uploaded-images/topo/" + theTopo.getId() + "3.png" );

                if ( topoImage1 != null && !topoImage1.isEmpty() ) {
                    try {
                        topoImage1.transferTo( new File( path1.toString() ) );
                        theTopo.setImage1( true );
                    } catch ( IllegalStateException | IOException e ) {
                        // oops! something did not work as expected
                        e.printStackTrace();
                        throw new RuntimeException( "Saving Topo image1 was not successful", e );
                    }
                } else {
                    theTopo.setImage1( false );
                }

                if ( topoImage2 != null && !topoImage2.isEmpty() ) {
                    try {
                        topoImage2.transferTo( new File( path2.toString() ) );
                        theTopo.setImage2( true );
                    } catch ( IllegalStateException | IOException e ) {
                        // oops! something did not work as expected
                        e.printStackTrace();
                        throw new RuntimeException( "Saving Topo image2 was not successful", e );
                    }
                } else {
                    theTopo.setImage2( false );
                }

                if ( topoImage3 != null && !topoImage3.isEmpty() ) {
                    try {
                        topoImage3.transferTo( new File( path3.toString() ) );
                        theTopo.setImage3( true );
                    } catch ( IllegalStateException | IOException e ) {
                        // oops! something did not work as expected
                        e.printStackTrace();
                        throw new RuntimeException( "Saving Topo image3 was not successful", e );
                    }
                } else {
                    theTopo.setImage3( false );
                }
                logger.info( "The Topo has been saved successfully : " + theTopo );
                topoService.saveTopo( theTopo );
                return "redirect:/topo/" + theTopo.getId() + "/vuetopo";
            }
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
    @GetMapping( "{topoId}/vuetopo" )
    public String formForTopoView( @PathVariable( "topoId" ) Integer topoId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) != null ) {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
        }
        Topo theTopo = topoService.getTopo( topoId );
        theModel.addAttribute( "topo", theTopo );
        logger.info( "The Topo is : " + theTopo );
        return "topo_view";
    }

    /*
     * ************************* Topo Update *************************
     */

    /**
     * Page to update topo
     * 
     * @param topoId
     *            the id of the topo
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return topo update view
     */
    @GetMapping( "{topoId}/majtopo" )
    public String formForTopoUpdate( @PathVariable( "topoId" ) Integer topoId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            theModel.addAttribute( "user", theUser );
            List<County> countys = countyService.getCountys();
            Map<Integer, String> nameCounty = countyService.getCountyNameOfCountys( countys );
            theModel.addAttribute( "county", nameCounty );
            UpdateTopo theTopo = new UpdateTopo();
            Topo topoToUpdate = topoService.getTopo( topoId );
            theModel.addAttribute( "topo", topoToUpdate );
            theTopo.setId( topoId );
            theTopo.setUpdateTopoName( topoToUpdate.getTopoName() );
            theTopo.setUpdateTopoCity( topoToUpdate.getTopoCity() );
            theTopo.setUpdateTopoCountry( topoToUpdate.getTopoCountry() );
            theTopo.setUpdateTopoDescriptive( topoToUpdate.getTopoDescriptive() );
            theTopo.setUpdateTopoReleaseDate( topoToUpdate.getTopoReleaseDate() );
            theTopo.setUser( topoToUpdate.getUser() );
            theTopo.setCounty( topoToUpdate.getCounty() );
            theTopo.setTopoCounty( topoToUpdate.countyOfTopo() );
            Integer topoUserId = theTopo.idUser();
            if ( !topoUserId.equals( userId ) ) {
                return "redirect:/{topoId}/vuetopo";
            } else {
                theModel.addAttribute( "updateTopo", theTopo );
                return "topo_update";
            }
        }
    }

    /**
     * Process after submit button click on topo_update page
     * 
     * @param topoId
     *            the id of the topo
     * @param theTopo
     *            the topo updated
     * @param result
     *            result of validation form
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the view of the topo updating
     */
    @PostMapping( "{topoId}/updatetopo" )
    public String updateTopo( @PathVariable( "topoId" ) Integer topoId,
            @Valid @ModelAttribute( "updateTopo" ) UpdateTopo theTopo, BindingResult result, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute( "idUser" );
        User theUser = userService.getUser( userId );
        theModel.addAttribute( "user", theUser );
        topoUpdateValidator.validate( theTopo, result );
        if ( result.hasErrors() ) {
            Topo topo = topoService.getTopo( topoId );
            List<County> countys = countyService.getCountys();
            Map<Integer, String> nameCounty = countyService.getCountyNameOfCountys( countys );
            theModel.addAttribute( "county", nameCounty );
            theModel.addAttribute( "user", theUser );
            theModel.addAttribute( "topo", topo );
            theModel.addAttribute( "updateTopo", theTopo );
            return "topo_update";
        } else {
            County theCounty = countyService.getCounty( theTopo.getTopoCounty() );
            Topo topoUpdate = topoService.getTopo( theTopo.getId() );
            topoUpdate.setTopoName( theTopo.getUpdateTopoName() );
            topoUpdate.setTopoCity( theTopo.getUpdateTopoCity() );
            topoUpdate.setTopoCounty( theTopo.getTopoCounty() );
            topoUpdate.setTopoCountry( theTopo.getUpdateTopoCountry() );
            topoUpdate.setTopoDescriptive( theTopo.getUpdateTopoDescriptive() );
            topoUpdate.setTopoReleaseDate( theTopo.getUpdateTopoReleaseDate() );
            topoUpdate.setCounty( theCounty );

            MultipartFile topoImage1 = theTopo.getUpdateTopoImage1();
            MultipartFile topoImage2 = theTopo.getUpdateTopoImage2();
            MultipartFile topoImage3 = theTopo.getUpdateTopoImage3();

            String rootDirectory = request.getSession().getServletContext().getRealPath( "/" );

            path1 = Paths.get( rootDirectory + "resources/uploaded-images/topo/" + topoUpdate.getId() + "1.png" );

            path2 = Paths.get( rootDirectory + "resources/uploaded-images/topo/" + topoUpdate.getId() + "2.png" );

            path3 = Paths.get( rootDirectory + "resources/uploaded-images/topo/" + topoUpdate.getId() + "3.png" );

            if ( topoImage1 != null && !topoImage1.isEmpty() ) {
                try {
                    topoImage1.transferTo( new File( path1.toString() ) );
                    topoUpdate.setImage1( true );
                } catch ( IllegalStateException | IOException e ) {
                    e.printStackTrace();
                    throw new RuntimeException( "Saving Topo image1 was not successful", e );
                }
            } else {
                if ( Files.exists( path1 ) ) {
                    topoUpdate.setImage1( true );
                } else {
                    topoUpdate.setImage1( false );
                }
            }

            if ( topoImage2 != null && !topoImage2.isEmpty() ) {
                try {
                    topoImage2.transferTo( new File( path2.toString() ) );
                    topoUpdate.setImage2( true );
                } catch ( IllegalStateException | IOException e ) {
                    e.printStackTrace();
                    throw new RuntimeException( "Saving Topo image2 was not successful", e );
                }
            } else {
                if ( Files.exists( path2 ) ) {
                    topoUpdate.setImage2( true );
                } else {
                    topoUpdate.setImage2( false );
                }
            }

            if ( topoImage3 != null && !topoImage3.isEmpty() ) {
                try {
                    topoImage3.transferTo( new File( path3.toString() ) );
                    topoUpdate.setImage3( true );
                } catch ( IllegalStateException | IOException e ) {
                    e.printStackTrace();
                    throw new RuntimeException( "Saving Topo image3 was not successful", e );
                }
            } else {
                if ( Files.exists( path3 ) ) {
                    topoUpdate.setImage3( true );
                } else {
                    topoUpdate.setImage3( false );
                }
            }
            topoService.updateTopo( topoUpdate );
            logger.info( "The Topo has been successfully updated : " + topoUpdate );
            return "redirect:/topo/" + topoId + "/vuetopo";
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
    @GetMapping( "{topoId}/deletetopo" )
    public String deleteTopo( @PathVariable( "topoId" ) Integer topoId, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            String rootDirectory = request.getSession().getServletContext().getRealPath( "/" );

            path1 = Paths.get( rootDirectory + "resources/uploaded-images/topo/" + topoId + "1.png" );

            path2 = Paths.get( rootDirectory + "resources/uploaded-images/topo/" + topoId + "2.png" );

            path3 = Paths.get( rootDirectory + "resources/uploaded-images/topo/" + topoId + "3.png" );

            if ( Files.exists( path1 ) ) {
                try {
                    Files.delete( path1 );
                } catch ( IOException e ) {
                    e.printStackTrace();
                    throw new RuntimeException( "Delete Topo image1 was not successful", e );
                }
            }

            if ( Files.exists( path2 ) ) {
                try {
                    Files.delete( path2 );
                } catch ( IOException e ) {
                    e.printStackTrace();
                    throw new RuntimeException( "Delete Topo image2 was not successful", e );
                }
            }

            if ( Files.exists( path3 ) ) {
                try {
                    Files.delete( path3 );
                } catch ( IOException e ) {
                    e.printStackTrace();
                    throw new RuntimeException( "Delete Topo image3 was not successful", e );
                }
            }
            logger.info( "The topo has been deleted  : " + topoService.getTopo( topoId ) );
            topoService.deleteTopo( topoId );
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
     * @return the view of the topo update with the booking of the topo
     */
    @GetMapping( "{topoId}/bookingtopo" )
    public String bookTopo( @PathVariable( "topoId" ) Integer topoId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User userTaker = userService.getUser( userId );
            Topo theTopo = topoService.getTopo( topoId );
            User userLender = theTopo.getUser();
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
            logger.info( "The topo : " + theTopo + " has been booked by : " + userTaker );
            return "redirect:/topo/" + topoId + "/vuetopo";
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
     * @return the view of the topo update with avaibility of the topo
     */
    @GetMapping( "{topoId}/availabilitytopo" )
    public String availableTopo( @PathVariable( "topoId" ) Integer topoId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Topo theTopo = topoService.getTopo( topoId );
            theTopo.setTopoAvailability( false );
            topoService.updateTopo( theTopo );
            logger.info( "The topo : " + theTopo + " is avaible now." );
            return "redirect:/topo/" + topoId + "/vuetopo";
        }
    }
}
