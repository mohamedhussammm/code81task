package code81.code81task.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    
    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }
    
    @GetMapping("/users")
    public String manageUsers() {
        return "admin-users";
    }
    
    @GetMapping("/books")
    public String manageBooks() {
        return "admin-books";
    }
    
    @GetMapping("/borrowings")
    public String manageBorrowings() {
        return "admin-borrowings";
    }
}