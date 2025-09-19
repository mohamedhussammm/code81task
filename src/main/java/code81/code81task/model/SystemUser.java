// package code81.code81task.model;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "system_user")
// public class SystemUser {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @Column(unique = true, nullable = false, length = 100)
//     private String username;
    
//     @Column(nullable = false, length = 255)
//     private String password;
    
//     @Enumerated(EnumType.STRING)
//     @Column(nullable = false, length = 20)
//     private Role role;
    
//     // Constructors
//     public SystemUser() {}
    
//     public SystemUser(String username, String password, Role role) {
//         this.username = username;
//         this.password = password;
//         this.role = role;
//     }
    
//     // Getters and setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
    
//     public String getUsername() { return username; }
//     public void setUsername(String username) { this.username = username; }
    
//     public String getPassword() { return password; }
//     public void setPassword(String password) { this.password = password; }
    
//     public Role getRole() { return role; }
//     public void setRole(Role role) { this.role = role; }
    
//     @Override
//     public String toString() {
//         return "SystemUser{" +
//                 "id=" + id +
//                 ", username='" + username + '\'' +
//                 ", role=" + role +
//                 '}';
//     }
// }