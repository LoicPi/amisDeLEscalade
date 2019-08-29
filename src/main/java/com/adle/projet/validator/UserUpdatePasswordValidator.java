package com.adle.projet.validator;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.dto.UpdatePasswordUser;
import com.adle.projet.entity.User;
import com.adle.projet.service.UserService;
import com.adle.projet.tools.PasswordEncryptor;

@Component
public class UserUpdatePasswordValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( UserValidator.class );

    @Autowired
    private UserService         userService;

    @Override
    public boolean supports( Class<?> clazz ) {
        return UpdatePasswordUser.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {
        UpdatePasswordUser user = (UpdatePasswordUser) obj;

        User userPasswordCheck = userService.getUser( user.getId() );

        Pattern patternPassword = Pattern.compile(
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$", Pattern.CASE_INSENSITIVE );

        if ( user.getOldPassword().equals( "" ) ) {
            logger.info( "Password is empty." );
            errors.rejectValue( "oldPassword", "userLoggValidator.password.empty" );
        }

        if ( user.getNewPassword().equals( "" ) ) {
            logger.info( "Password is empty." );
            errors.rejectValue( "newPassword", "userLoggValidator.password.empty" );
        }

        if ( user.getConfirmPassword().equals( "" ) ) {
            logger.info( "Password is empty." );
            errors.rejectValue( "confirmPassword", "userLoggValidator.password.empty" );
        }

        if ( !user.getNewPassword().equals( "" ) && !( patternPassword.matcher( user.getNewPassword() ).matches() ) ) {
            logger.info( "Password is not correct." );
            errors.rejectValue( "newPassword", "userValidator.password.invalid" );
        }

        if ( !( user.getNewPassword().equals( user.getConfirmPassword() ) ) ) {
            logger.info( "Password and PasswordControl is not the same." );
            errors.rejectValue( "confirmPassword", "userValidator.passwordControl.invalid" );
        }

        if ( !( PasswordEncryptor.checkPassword( user.getOldPassword(), userPasswordCheck.getPassword() ) ) ) {
            logger.info( "The old password is not correct." );
            errors.rejectValue( "oldPassword", "userUpdateValidator.oldPassword.invalid" );
        }
    }
}
