// package code81.code81task.service;

// import code81.code81task.model.SystemUser;
// import code81.code81task.model.UserActivity;
// import code81.code81task.repository.UserActivityRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class UserActivityService {
    
//     @Autowired
//     private UserActivityRepository userActivityRepository;
    
//     @Autowired
//     private UserService userService;
    
//     public List<UserActivity> findAll() {
//         return userActivityRepository.findAll();
//     }
    
//     public UserActivity save(UserActivity activity) {
//         return userActivityRepository.save(activity);
//     }
    
//     public List<UserActivity> findByUserId(Long userId) {
//         return userActivityRepository.findByUserId(userId);
//     }
    
//     public List<UserActivity> findByTimestampBetween(LocalDateTime start, LocalDateTime end) {
//         return userActivityRepository.findByTimestampBetween(start, end);
//     }
    
//     public List<UserActivity> findByAction(String action) {
//         return userActivityRepository.findByActionContainingIgnoreCase(action);
//     }
    
//     public void logActivity(String action) {
//         logActivity(action, null);
//     }
    
//     public void logActivity(String action, String details) {
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//         if (authentication != null && authentication.isAuthenticated()) {
//             String username = authentication.getName();
//             SystemUser user = userService.findByUsername(username).orElse(null);
            
//             if (user != null) {
//                 UserActivity activity = new UserActivity(user, action, details);
//                 save(activity);
//             }
//         }
//     }
// }