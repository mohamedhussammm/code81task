package code81.code81task.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "admin123"; // Change this to your desired password
        String encodedPassword = encoder.encode(password);
        System.out.println("Encoded password: " + encodedPassword);
    }
}