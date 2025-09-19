package code81.code81task.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import code81.code81task.model.Role;
import code81.code81task.model.User;
import code81.code81task.security.UserPrincipal;
import code81.code81task.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN') or #id == principal.id")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/role/{role}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public List<User> getUsersByRole(@PathVariable Role role) {
        return userService.getUsersByRole(role);
    }
    @GetMapping("/me")
public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
    if (userPrincipal == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
    }
    
    Optional<User> user = userService.getUserById(userPrincipal.getId());
    if (user.isPresent()) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.get().getId());
        response.put("username", user.get().getUsername());
        response.put("email", user.get().getEmail());
        response.put("role", user.get().getRole().name());
        return ResponseEntity.ok(response);
    }
    return ResponseEntity.notFound().build();
}
}