// package code81.code81task.repository;

// import code81.code81task.entity.Role;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.Optional;

// @Repository
// public interface RoleRepository extends JpaRepository<Role, Long> {
//     Optional<Role> findByName(Role.RoleName name);
//     boolean existsByName(Role.RoleName name);
// }