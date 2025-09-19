package code81.code81task.repository;

import code81.code81task.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    @Query("SELECT b FROM Book b WHERE b.availableCopies > 0")
    List<Book> findAvailableBooks();
    
    @Query("SELECT b FROM Book b JOIN b.authors a WHERE LOWER(a.firstName) LIKE LOWER(CONCAT('%', :author, '%')) OR LOWER(a.lastName) LIKE LOWER(CONCAT('%', :author, '%'))")
    List<Book> findByAuthorNameContainingIgnoreCase(@Param("author") String author);
}