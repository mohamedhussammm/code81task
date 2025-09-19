package code81.code81task.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import code81.code81task.model.BorrowStatus;
import code81.code81task.model.BorrowingRecord;
import code81.code81task.service.BorrowingService;

@RestController
@RequestMapping("/api/borrowing")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingService.getAllBorrowingRecords();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public ResponseEntity<BorrowingRecord> getBorrowingRecordById(@PathVariable Long id) {
        Optional<BorrowingRecord> record = borrowingService.getBorrowingRecordById(id);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN') or #userId == principal.id")
    public List<BorrowingRecord> getBorrowingRecordsByUser(@PathVariable Long userId) {
        return borrowingService.getBorrowingRecordsByUser(userId);
    }

    @GetMapping("/book/{bookId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public List<BorrowingRecord> getBorrowingRecordsByBook(@PathVariable Long bookId) {
        return borrowingService.getBorrowingRecordsByBook(bookId);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public List<BorrowingRecord> getBorrowingRecordsByStatus(@PathVariable BorrowStatus status) {
        return borrowingService.getBorrowingRecordsByStatus(status);
    }

    @GetMapping("/overdue")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public List<BorrowingRecord> getOverdueRecords() {
        return borrowingService.getOverdueRecords();
    }

    @PostMapping("/borrow")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public ResponseEntity<BorrowingRecord> borrowBook(@RequestParam Long bookId,
                                                      @RequestParam Long userId,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
        BorrowingRecord record = borrowingService.borrowBook(bookId, userId, dueDate);
        if (record != null) {
            return ResponseEntity.ok(record);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/return/{recordId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable Long recordId) {
        BorrowingRecord record = borrowingService.returnBook(recordId);
        if (record != null) {
            return ResponseEntity.ok(record);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public ResponseEntity<?> deleteBorrowingRecord(@PathVariable Long id) {
        borrowingService.deleteBorrowingRecord(id);
        return ResponseEntity.ok().build();
    }
}
