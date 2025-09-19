// package code81.code81task.model;


// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;

// import java.util.Set;
// @Entity
// @Table(name = "member")
// public class Member {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @Column(name = "first_name", nullable = false)
//     private String firstName;
    
//     @Column(name = "last_name", nullable = false)
//     private String lastName;
    
//     @Column(nullable = false, unique = true)
//     private String email;
    
//     private String phone;
//     private String address;
    
//     @OneToMany(mappedBy = "member")
//     private Set<BorrowingRecord> borrowTransactions;
    
//     public Member() {}
    
//     public Member(String firstName, String lastName, String email, String phone, String address) {
//         this.firstName = firstName;
//         this.lastName = lastName;
//         this.email = email;
//         this.phone = phone;
//         this.address = address;
//     }
    
//     // Getters and setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
    
//     public String getFirstName() { return firstName; }
//     public void setFirstName(String firstName) { this.firstName = firstName; }
    
//     public String getLastName() { return lastName; }
//     public void setLastName(String lastName) { this.lastName = lastName; }
    
//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }
    
//     public String getPhone() { return phone; }
//     public void setPhone(String phone) { this.phone = phone; }
    
//     public String getAddress() { return address; }
//     public void setAddress(String address) { this.address = address; }
    
//     public Set<BorrowingRecord> getBorrowTransactions() { return borrowTransactions; }
//     public void setBorrowTransactions(Set<BorrowingRecord> borrowTransactions) { 
//         this.borrowTransactions = borrowTransactions; 
//     }
// }