package cj.cloudwave.member.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// Password 암호화 방식
@Component
public class PasswordUtil {

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
