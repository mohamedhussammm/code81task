// package code81.code81task.config;

// import code81.code81task.entity.SystemUser;
// import code81.code81task.repository.SystemUserRepository;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.*;
// import org.springframework.stereotype.Service;

// import java.util.Set;
// import java.util.stream.Collectors;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//   private final SystemUserRepository userRepository;

//   public CustomUserDetailsService(SystemUserRepository userRepository){
//     this.userRepository = userRepository;
//   }

//   @Override
//   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//     SystemUser u = userRepository.findByUsername(username)
//       .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//     Set<GrantedAuthority> authorities = u.getRoles().stream()
//       .map(r -> new SimpleGrantedAuthority(r.getName()))
//       .collect(Collectors.toSet());
//     return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), authorities);
//   }
// }
