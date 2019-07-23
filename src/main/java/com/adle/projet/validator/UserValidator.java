package com.adle.projet.validator;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.entity.User;
import com.adle.projet.service.UserService;

@Component
public class UserValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( UserValidator.class );

    @Autowired
    private UserService         userService;

    @Override
    public boolean supports( Class<?> clazz ) {
        return User.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {
        User user = (User) obj;

        Pattern patternEmail = Pattern.compile( "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE );

        Pattern patternPassword = Pattern.compile(
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$", Pattern.CASE_INSENSITIVE );

        if ( !( patternEmail.matcher( user.getEmail() ).matches() ) ) {
            logger.info( "Email is not correct." );
            errors.rejectValue( "email", "userValidator.email.invalid" );
        }

        if ( userService.findUserWithEmail( user.getEmail() ).isPresent() ) {
            logger.info( "Email already exists in the database." );
            errors.rejectValue( "email", "userValidator.email.know" );
        }

        if ( userService.findUserWithNickName( user.getNickName() ).isPresent() ) {
            logger.info( "NickName already exists in the database." );
            errors.rejectValue( "nickName", "userValidator.nickName.know" );
        }
        if ( !( patternPassword.matcher( user.getPassword() ).matches() ) ) {
            logger.info( "Password is not correct." );
            errors.rejectValue( "password", "userValidator.password.invalid" );
        }
    }

}
