// package code81.code81task.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import code81.code81task.model.Member;

// import java.util.List;
// import java.util.Optional;

// @Repository
// public interface MemberRepository extends JpaRepository<Member, Long> {
//     Optional<Member> findByEmail(String email);
//     List<Member> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
// }