package com.adle.projet.tools;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Class to encrypt the password and control the password with password
 * encrypted in database
 * 
 * @author Loïc
 *
 */

public class PasswordEncryptor {

    private static int workload = 12;

    public static String hashPassword( String passwordUser ) {
        String salt = BCrypt.gensalt( workload );
        String hashed_password = BCrypt.hashpw( passwordUser, salt );
        return hashed_password;
    }

    public static boolean checkPassword( String passwordLogg, String passwordUserStored ) {
        boolean password_verified = false;

        if ( null == passwordUserStored || !passwordUserStored.startsWith( "$2a$" ) )
            throw new java.lang.IllegalArgumentException( "Invalid password hash provided for comparison" );

        password_verified = BCrypt.checkpw( passwordLogg, passwordUserStored );

        return ( password_verified );
    }

}
