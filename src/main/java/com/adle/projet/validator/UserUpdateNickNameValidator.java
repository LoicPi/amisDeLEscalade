package com.adle.projet.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adle.projet.dto.UpdateUser;
import com.adle.projet.service.UserService;

@Component
public class UserUpdateNickNameValidator implements Validator {

    private static final Logger logger = LogManager.getLogger( UserUpdateNickNameValidator.class );

    @Autowired
    private UserService         userService;

    @Override
    public boolean supports( Class<?> clazz ) {
        return UpdateUser.class.equals( clazz );
    }

    @Override
    public void validate( Object obj, Errors errors ) {
        UpdateUser user = (UpdateUser) obj;

        String userNickName = user.getNickName();

        if ( userNickName.equals( "" ) ) {
            logger.info( "NickName is empty." );
            errors.rejectValue( "nickName", "userUpdateNickNameValidator.nickName.empty" );
        }

        if ( userService.findUserWithNickName( userNickName ).isPresent() ) {
            logger.info( "NickName already exists in the database." );
            errors.rejectValue( "nickName", "userValidator.nickName.know" );
        }

    }

}
