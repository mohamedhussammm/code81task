// package code81.code81task.config;

// import code81.code81task.entity.Role;
// import code81.code81task.entity.SystemUser;
// import code81.code81task.service.SystemUserService;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class DataInitializer {

//     public void initializeDefaultUsers() {
//     System.out.println("Initializing default users...");
    
//     // Check and create admin user
//     if (!existsByUsername("admin")) {
//         createUser("admin", "admin", Role.ADMIN);
//         System.out.println("Created default admin user");
//     } else {
//         System.out.println("Admin user already exists");
//     }
    
//     // Check and create librarian user
//     if (!existsByUsername("librarian")) {
//         createUser("librarian", "librarian", Role.LIBRARIAN);
//         System.out.println("Created default librarian user");
//     } else {
//         System.out.println("Librarian user already exists");
//     }
    
//     // Check and create staff user
//     if (!existsByUsername("staff")) {
//         createUser("staff", "staff", Role.STAFF);
//         System.out.println("Created default staff user");
//     } else {
//         System.out.println("Staff user already exists");
//     }
    
//     // Debug: List all users
//     List<SystemUser> users = findAll();
//     System.out.println("Total users in database: " + users.size());
//     for (SystemUser user : users) {
//         System.out.println("User: " + user.getUsername() + ", Role: " + user.getRole() + ", Password: " + user.getPassword());
//     }
// }
// }
