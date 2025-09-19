// package code81.code81task.security;

// import java.util.Set;
// import java.util.stream.Collectors;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import code81.code81task.entity.User;
// import code81.code81task.repository.UserRepository;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     private final UserRepository userRepo;

//     public CustomUserDetailsService(UserRepository userRepo) {
//         this.userRepo = userRepo;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User u = userRepo.findByUsername(username)
//                 .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

//         Set<GrantedAuthority> authorities = u.getRoles().stream()
//                 .map(r -> new SimpleGrantedAuthority(r.getName()))
//                 .collect(Collectors.toSet());

//         return new org.springframework.security.core.userdetails.User(
//                 u.getUsername(),
//                 u.getPassword(),
//                 u.isEnabled(),
//                 true, true, true,
//                 authorities
//         );
//     }
// }
