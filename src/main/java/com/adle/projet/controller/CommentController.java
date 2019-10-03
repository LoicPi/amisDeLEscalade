package com.adle.projet.controller;

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

import com.adle.projet.dto.UpdateComment;
import com.adle.projet.entity.Comment;
import com.adle.projet.entity.Spot;
import com.adle.projet.entity.User;
import com.adle.projet.service.CommentService;
import com.adle.projet.service.SpotService;
import com.adle.projet.service.UserService;
import com.adle.projet.validator.CommentUpdateValidator;
import com.adle.projet.validator.CommentValidator;

@Controller
@RequestMapping( "/site/{spotId}/commentaire" )
public class CommentController {

    @Autowired
    private UserService            userService;

    @Autowired
    private SpotService            spotService;

    @Autowired
    private CommentService         commentService;

    @Autowired
    private CommentValidator       commentValidator;

    @Autowired
    private CommentUpdateValidator commentUpdateValidator;

    /*
     * *********************** Add a Comment to the Spot ***********************
     */

    /**
     * Page to add comment on spot
     * 
     * @param spotId
     *            the id of the spot
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the add-comment page
     */
    @GetMapping( "/ajoutercommentaire" )
    public String addACommentToTheSpot( @PathVariable( "spotId" ) Integer spotId, Model theModel,
            HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            Spot theSpot = spotService.getSpot( spotId );
            Comment theComment = new Comment();
            theModel.addAttribute( "user", theUser );
            theModel.addAttribute( "spot", theSpot );
            theModel.addAttribute( "comment", theComment );
            return "comment_add";
        }
    }

    /**
     * Process after submit button click on comment-add page
     * 
     * @param theComment
     *            comment create on comment_add page
     * @param spotId
     *            the id of the spot
     * @param theModel
     *            attribute to page jsp
     * @param result
     *            result of validation form
     * @param request
     *            information on the session
     * @return the view of the spot updating
     */
    @PostMapping( "/savecomment" )
    public String saveComment( @ModelAttribute( "comment" ) Comment theComment,
            @PathVariable( "spotId" ) Integer spotId, Model theModel, BindingResult result,
            HttpServletRequest request ) {

        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            commentValidator.validate( theComment, result );
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            Spot theSpot = spotService.getSpot( spotId );
            if ( result.hasErrors() ) {
                theModel.addAttribute( "user", theUser );
                theModel.addAttribute( "spot", theSpot );
                theModel.addAttribute( "comment", theComment );
                return "comment_add";
            } else {
                theComment.setUser( theUser );
                theComment.setSpot( theSpot );
                commentService.saveComment( theComment );
                return "redirect:/site/" + spotId + "/vuesite";
            }
        }
    }

    /*
     * ********************** Edit a Comment of the Spot **********************
     */

    /**
     * Page to update comment
     * 
     * @param spotId
     *            the id of the spot
     * @param commentId
     *            the id of the comment
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the page to edit a comment
     */
    @GetMapping( "{commentId}/modifiercommentaire" )
    public String editACommentOfTheSpot( @PathVariable( "spotId" ) Integer spotId,
            @PathVariable( "commentId" ) Integer commentId, Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            Integer userId = (Integer) session.getAttribute( "idUser" );
            User theUser = userService.getUser( userId );
            Spot theSpot = spotService.getSpot( spotId );
            UpdateComment theComment = new UpdateComment();
            Comment commentToUpdate = commentService.getComment( commentId );
            theComment.setId( commentId );
            theComment.setUpdateContents( commentToUpdate.getContents() );
            if ( !( theUser.isMember() ) ) {
                return "redirect:/site/" + spotId + "/vuesite";
            } else {
                theModel.addAttribute( "user", theUser );
                theModel.addAttribute( "spot", theSpot );
                theModel.addAttribute( "updateComment", theComment );
                return "comment_edit";
            }
        }
    }

    /**
     * Process after submit button click on comment_edit page
     * 
     * @param spotId
     *            the id of the spot
     * @param commentId
     *            the id of the comment
     * @param theComment
     *            the comment updated
     * @param result
     *            result of validation form
     * @param theModel
     *            attribute to page jsp
     * @param request
     *            information on the session
     * @return the view of the spot updating
     */
    @PostMapping( "/{commentId}/updatecomment" )
    public String updateSpot( @PathVariable( "spotId" ) Integer spotId, @PathVariable( "commentId" ) Integer commentId,
            @Valid @ModelAttribute( "updateComment" ) UpdateComment theComment, BindingResult result,
            Model theModel, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute( "idUser" );
        User theUser = userService.getUser( userId );
        Spot theSpot = spotService.getSpot( spotId );
        Comment comment = commentService.getComment( commentId );
        commentUpdateValidator.validate( theComment, result );
        if ( result.hasErrors() ) {
            theModel.addAttribute( "user", theUser );
            theModel.addAttribute( "spot", theSpot );
            theModel.addAttribute( "comment", comment );
            theModel.addAttribute( "updateComment", theComment );
            return "comment_edit";
        } else {
            comment.setContents( theComment.getUpdateContents() );
            commentService.updateComment( comment );
            return "redirect:/site/" + spotId + "/vuesite";
        }
    }

    /*
     * ********************* Delete a Comment of the Spot *********************
     */

    /**
     * Command to delete a comment
     * 
     * @param spotId
     *            the id of the spot
     * @param commentId
     *            the id of the comment
     * @param request
     *            information on the session
     * @return the view of the spot updating
     */
    @GetMapping( "{commentId}/deletecomment" )
    public String deleteComment( @PathVariable( "spotId" ) Integer spotId,
            @PathVariable( "commentId" ) Integer commentId, HttpServletRequest request ) {
        HttpSession session = request.getSession();
        if ( session.getAttribute( "idUser" ) == null ) {
            return "redirect:/compte/connexion";
        } else {
            commentService.deleteComment( commentId );
            return "redirect:/site/" + spotId + "/vuesite";
        }
    }

}
