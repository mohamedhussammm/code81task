package code81.code81task.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import code81.code81task.model.BorrowStatus;
import code81.code81task.model.BorrowingRecord;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    List<BorrowingRecord> findByUserId(Long userId);
    List<BorrowingRecord> findByBookId(Long bookId);
    List<BorrowingRecord> findByStatus(BorrowStatus status);
    
    @Query("SELECT br FROM BorrowingRecord br WHERE br.dueDate < :currentDate AND br.status = 'BORROWED'")
    List<BorrowingRecord> findOverdueRecords(@Param("currentDate") LocalDate currentDate);
    
    Optional<BorrowingRecord> findByBookIdAndUserIdAndStatus(Long bookId, Long userId, BorrowStatus status);
}