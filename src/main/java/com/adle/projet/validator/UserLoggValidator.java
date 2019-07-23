package com.adle.projet.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.entity.User;
import com.adle.projet.service.UserService;
import com.adle.projet.tools.PasswordEncryptor;

@Component
public class UserLoggValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( UserLoggValidator.class );

    @Autowired
    private UserService         userService;

    @Override
    public boolean supports( Class<?> clazz ) {
        return User.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {
        User user = (User) obj;

        String userEmail = user.getEmail();
        String userPassword = user.getPassword();

        if ( userEmail.equals( "" ) ) {
            logger.info( "Email is emmpty." );
            errors.rejectValue( "email", "userLoggValidator.email.empty" );
        }
        if ( userPassword.equals( "" ) ) {
            logger.info( "Password is emmpty." );
            errors.rejectValue( "password", "userLoggValidator.password.empty" );
        }
        if ( !userEmail.equals( "" ) && !( userService.findUserWithEmail( userEmail ) ).isPresent() ) {
            logger.info( "Email does not exist." );
            errors.rejectValue( "email", "userLoggValidator.email.exist" );
        }
        if ( !userPassword.equals( "" ) && userService.findUserWithEmail( userEmail ).isPresent() ) {
            logger.info( "Email exist." );
            User userCheck = userService.findUserByEmail( userEmail ).get( 0 );
            logger.info( "UserCheck : " + userCheck );
            if ( !( PasswordEncryptor.checkPassword( userPassword, userCheck.getPassword() ) ) ) {
                logger.info( "Password is not correct." );
                errors.rejectValue( "password", "userLoggValidator.password.wrong" );
            }
        }
    }
}
