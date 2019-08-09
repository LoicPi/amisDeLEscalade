package com.adle.projet.validator;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.entity.User;

@Component
public class UserUpdatePasswordValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( UserValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return User.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {
        User user = (User) obj;

        String userPassword = user.getPassword();

        Pattern patternPassword = Pattern.compile(
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$", Pattern.CASE_INSENSITIVE );

        if ( userPassword.equals( "" ) ) {
            logger.info( "Password is empty." );
            errors.rejectValue( "password", "userLoggValidator.password.empty" );
        }

        if ( !userPassword.equals( "" ) && !( patternPassword.matcher( user.getPassword() ).matches() ) ) {
            logger.info( "Password is not correct." );
            errors.rejectValue( "password", "userValidator.password.invalid" );
        }
    }

}
