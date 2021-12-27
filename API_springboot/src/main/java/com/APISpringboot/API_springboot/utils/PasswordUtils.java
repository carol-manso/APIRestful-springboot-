package com.APISpringboot.API_springboot.utils;

import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.logging.Logger;

public class PasswordUtils {
    private static final Logger log = (Logger) LoggerFactory.getLogger(PasswordUtils.class);
    public PasswordUtils(){

    }
/*    Genrate a hash using ByCript.
        @param password
        @return String
 */
    public static String genereateBCrypt(String password){
        if(password == null)
            return password;
        log.info("Generating a hash with ByCript");
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.encode(password); //retorna o hash da senha que o usuário digitar

    }


}
