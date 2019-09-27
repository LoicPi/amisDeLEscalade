package com.adle.projet.validator;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.dto.UpdateUser;
import com.adle.projet.entity.User;
import com.adle.projet.service.UserService;

@Component
public class UserUpdateValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( UserUpdateValidator.class );

    @Autowired
    private UserService         userService;

    @Override
    public boolean supports( Class<?> clazz ) {
        return UpdateUser.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {
        UpdateUser updateUser = (UpdateUser) obj;

        User user = userService.getUser( updateUser.getId() );

        String userFirstName = updateUser.getUpdateFirstName();

        String userLastName = updateUser.getUpdateLastName();

        String userNickName = updateUser.getUpdateNickName();

        String userEmail = updateUser.getUpdateEmail();

        Pattern patternEmail = Pattern.compile( "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE );

        if ( userEmail.equals( "" ) ) {
            logger.info( "UpdateEmail is empty." );
            errors.rejectValue( "updateEmail", "userLoggValidator.email.empty" );
        }

        if ( !( patternEmail.matcher( updateUser.getUpdateEmail() ).matches() ) ) {
            logger.info( "UpdateEmail is not correct." );
            errors.rejectValue( "updateEmail", "user.email.invalid" );
        }

        if ( !( user.getEmail().equals( userEmail ) ) ) {
            if ( userService.findUserWithEmail( updateUser.getUpdateEmail() ).isPresent() ) {
                logger.info( "UpdateEmail already exists in the database." );
                errors.rejectValue( "updateEmail", "userValidator.email.know" );
            }
        }

        if ( userNickName.equals( "" ) ) {
            logger.info( "UpdateNickName is empty." );
            errors.rejectValue( "updateNickName", "userUpdateValidator.nickName.empty" );
        }

        if ( !( user.getNickName().contentEquals( userNickName ) ) ) {
            if ( userService.findUserWithNickName( userNickName ).isPresent() ) {
                logger.info( "UpdateNickName already exists in the database." );
                errors.rejectValue( "updateNickName", "userValidator.nickName.know" );
            }
        }

        if ( userNickName.length() > 50 || userNickName.length() < 3 ) {
            logger.info( "UpdateLength of NickName is not correct." );
            errors.rejectValue( "updateNickName", "user.nickName.invalid" );
        }

        if ( userFirstName.length() > 50 || userFirstName.length() < 3 ) {
            logger.info( "UpdateLength of FirstName is not correct." );
            errors.rejectValue( "updateFirstName", "user.firstName.invalid" );
        }

        if ( userLastName.length() > 50 || userLastName.length() < 3 ) {
            logger.info( "UpdateLength of LastName is not correct." );
            errors.rejectValue( "updateLastName", "user.lastName.invalid" );
        }

    }

}
