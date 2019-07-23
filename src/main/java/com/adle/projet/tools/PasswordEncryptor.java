package com.adle.projet.tools;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {

    private static int workload = 12;

    public static String hashPassword( String passwordUser ) {
        String salt = BCrypt.gensalt( workload );
        String hashed_password = BCrypt.hashpw( passwordUser, salt );
        return hashed_password;
    }

}
