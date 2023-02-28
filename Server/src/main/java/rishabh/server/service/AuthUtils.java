package rishabh.server.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class AuthUtils {
    
    private static int STRENGTH = 10;
    
    static private BCryptPasswordEncoder bCryptPasswordEncoder =
            new BCryptPasswordEncoder(STRENGTH, new SecureRandom());
    
    static String salt(String plainPassword){
        return bCryptPasswordEncoder.encode(plainPassword);
    }
    
    static boolean match(String password,String encodedPassword){
        return bCryptPasswordEncoder.matches(password,encodedPassword);
    }
    
}
