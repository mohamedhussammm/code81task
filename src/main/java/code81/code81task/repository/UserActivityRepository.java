package code81.code81task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code81.code81task.model.UserActivity;
@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    // Remove or fix this method if it exists:
    // List<UserActivity> findByActionContainingIgnoreCase(String action);
    
    // Replace with correct method based on your actual properties:
    List<UserActivity> findByDescriptionContainingIgnoreCase(String description);
    
    // Or if you want to search by activity type (if you have that property):
    // List<UserActivity> findByActivityTypeContainingIgnoreCase(String activityType);
}