package com.donatus.activity_tracker_api.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordHash {
    private static final Integer workLoad = 12;

    public static String encryptPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(workLoad));
    }

    public static boolean isPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }
}
