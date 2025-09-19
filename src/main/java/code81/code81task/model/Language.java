// package code81.code81task.model;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "languages")
// public class Language {
//     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable=false, unique=true)
//     private String name;

//     @Column(length = 10)
//     private String code;

//     public Language() {}
//     public Language(String name, String code) { this.name = name; this.code = code; }

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
//     public String getName() { return name; }
//     public void setName(String name) { this.name = name; }
//     public String getCode() { return code; }
//     public void setCode(String code) { this.code = code; }
// }
