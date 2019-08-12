package com.adle.projet.validator;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.dto.UpdateUser;

@Component
public class UserUpdatePasswordValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( UserValidator.class );

    @Override
    public boolean supports( Class<?> clazz ) {
        return UpdateUser.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {
        UpdateUser user = (UpdateUser) obj;

        Pattern patternPassword = Pattern.compile(
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$", Pattern.CASE_INSENSITIVE );

        if ( user.getOldPassword().equals( "" ) ) {
            logger.info( "Password is empty." );
            errors.rejectValue( "password", "userLoggValidator.password.empty" );
        }

        if ( user.getNewPassword().equals( "" ) ) {
            logger.info( "Password is empty." );
            errors.rejectValue( "password", "userLoggValidator.password.empty" );
        }

        if ( user.getConfirmPassword().equals( "" ) ) {
            logger.info( "Password is empty." );
            errors.rejectValue( "password", "userLoggValidator.password.empty" );
        }

        if ( !user.getNewPassword().equals( "" ) && !( patternPassword.matcher( user.getNewPassword() ).matches() ) ) {
            logger.info( "Password is not correct." );
            errors.rejectValue( "password", "userValidator.password.invalid" );
        }

        if ( !( user.getNewPassword().equals( user.getConfirmPassword() ) ) ) {
            logger.info( "Password and PasswordControl is not the same." );
            errors.rejectValue( "passwordControl", "userValidator.passwordControl.invalid" );
        }
    }
}
