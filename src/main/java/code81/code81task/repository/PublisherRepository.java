package code81.code81task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code81.code81task.model.Publisher;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    List<Publisher> findByNameContainingIgnoreCase(String name);
}