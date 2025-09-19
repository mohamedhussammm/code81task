package code81.code81task.service;
import code81.code81task.model.User;

import code81.code81task.model.Book;
import code81.code81task.model.BorrowingRecord;
import code81.code81task.model.BorrowStatus;


import code81.code81task.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class BorrowingService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private UserService userService;

    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    public Optional<BorrowingRecord> getBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id);
    }

    public List<BorrowingRecord> getBorrowingRecordsByUser(Long userId) {
        return borrowingRecordRepository.findByUserId(userId);
    }

    public List<BorrowingRecord> getBorrowingRecordsByBook(Long bookId) {
        return borrowingRecordRepository.findByBookId(bookId);
    }

    public List<BorrowingRecord> getBorrowingRecordsByStatus(BorrowStatus status) {
        return borrowingRecordRepository.findByStatus(status);
    }

    public List<BorrowingRecord> getOverdueRecords() {
        return borrowingRecordRepository.findOverdueRecords(LocalDate.now());
    }

    public BorrowingRecord borrowBook(Long bookId, Long userId, LocalDate dueDate) {
        Optional<Book> bookOptional = bookService.getBookById(bookId);
        Optional<User> userOptional = userService.getUserById(userId);
        
        if (bookOptional.isPresent() && userOptional.isPresent()) {
            Book book = bookOptional.get();
            User user = userOptional.get();
            
            if (book.getAvailableCopies() > 0) {
                BorrowingRecord record = new BorrowingRecord();
                record.setBook(book);
                record.setUser(user);
                record.setBorrowDate(LocalDate.now());
                record.setDueDate(dueDate);
                record.setStatus(BorrowStatus.BORROWED);
                
                // Update book available copies
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                bookService.updateBook(bookId, book);
                
                return borrowingRecordRepository.save(record);
            }
        }
        return null;
    }

    public BorrowingRecord returnBook(Long recordId) {
        Optional<BorrowingRecord> recordOptional = borrowingRecordRepository.findById(recordId);
        if (recordOptional.isPresent()) {
            BorrowingRecord record = recordOptional.get();
            record.setReturnDate(LocalDate.now());
            record.setStatus(BorrowStatus.RETURNED);
            
            // Calculate fine if overdue
            if (record.getReturnDate().isAfter(record.getDueDate())) {
                long daysOverdue = record.getReturnDate().toEpochDay() - record.getDueDate().toEpochDay();
                record.setFineAmount(daysOverdue * 1.0); // $1 per day overdue
            }
            
            // Update book available copies
            Book book = record.getBook();
            book.setAvailableCopies(book.getAvailableCopies() + 1);
            bookService.updateBook(book.getId(), book);
            
            return borrowingRecordRepository.save(record);
        }
        return null;
    }

    public void deleteBorrowingRecord(Long id) {
        borrowingRecordRepository.deleteById(id);
    }
}