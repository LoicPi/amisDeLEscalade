package com.adle.projet.validator;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.dto.UpdateUser;
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
        UpdateUser user = (UpdateUser) obj;

        String userFirstName = user.getFirstName();

        String userLastName = user.getLastName();

        String userNickName = user.getNickName();

        String userEmail = user.getEmail();

        Pattern patternEmail = Pattern.compile( "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE );

        if ( userEmail.equals( "" ) ) {
            logger.info( "Email is empty." );
            errors.rejectValue( "email", "userLoggValidator.email.empty" );
        }

        if ( !userEmail.equals( "" ) && !( patternEmail.matcher( user.getEmail() ).matches() ) ) {
            logger.info( "Email is not correct." );
            errors.rejectValue( "email", "user.email.invalid" );
        }

        if ( userService.findUserWithEmail( user.getEmail() ).isPresent() ) {
            logger.info( "Email already exists in the database." );
            errors.rejectValue( "email", "userValidator.email.know" );
        }

        if ( userNickName.equals( "" ) ) {
            logger.info( "NickName is empty." );
            errors.rejectValue( "nickName", "userUpdateValidator.nickName.empty" );
        }

        if ( userService.findUserWithNickName( userNickName ).isPresent() ) {
            logger.info( "NickName already exists in the database." );
            errors.rejectValue( "nickName", "userValidator.nickName.know" );
        }

        if ( userNickName.length() > 20 || userNickName.length() < 3 ) {
            logger.info( "Length of NickName is not correct." );
            errors.rejectValue( "nickName", "user.nickName.invalid" );
        }

        if ( userFirstName.length() > 20 || userFirstName.length() < 3 ) {
            logger.info( "Length of FirstName is not correct." );
            errors.rejectValue( "firstName", "user.firstName.invalid" );
        }

        if ( userLastName.length() > 20 || userLastName.length() < 3 ) {
            logger.info( "Length of LastName is not correct." );
            errors.rejectValue( "lastName", "user.lastName.invalid" );
        }

    }

}
