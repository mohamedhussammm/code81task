package code81.code81task.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import code81.code81task.model.Role;   // ✅ correct import
import code81.code81task.model.User;
import code81.code81task.security.JwtUtils;
import code81.code81task.security.UserPrincipal;
import code81.code81task.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;
@PostMapping("/signin")
public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), 
                    loginRequest.getPassword()
            )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

    Map<String, Object> response = new HashMap<>();
    response.put("token", jwt);
    response.put("id", userDetails.getId());
    response.put("username", userDetails.getUsername());
    response.put("email", userDetails.getEmail());
    response.put("role", userDetails.getAuthorities().iterator().next().getAuthority());

    return ResponseEntity.ok(response);
}

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // ✅ encode password before saving
        String encodedPassword = encoder.encode(signUpRequest.getPassword());

        // Create new user's account
        User user = new User(
                signUpRequest.getUsername(),
                encodedPassword,
                signUpRequest.getEmail(),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                Role.MEMBER
        );

        userService.createUser(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}

// ✅ DTOs
class LoginRequest {
    private String username;
    private String password;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

class SignupRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
