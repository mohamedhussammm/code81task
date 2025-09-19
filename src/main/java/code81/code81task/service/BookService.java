package code81.code81task.service;

import code81.code81task.model.Book;
import code81.code81task.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setTitle(bookDetails.getTitle());
            book.setIsbn(bookDetails.getIsbn());
            book.setPublicationYear(bookDetails.getPublicationYear());
            book.setEdition(bookDetails.getEdition());
            book.setLanguage(bookDetails.getLanguage());
            book.setSummary(bookDetails.getSummary());
            book.setCoverImage(bookDetails.getCoverImage());
            book.setTotalCopies(bookDetails.getTotalCopies());
            book.setAvailableCopies(bookDetails.getAvailableCopies());
            book.setCategory(bookDetails.getCategory());
            book.setPublisher(bookDetails.getPublisher());
            book.setAuthors(bookDetails.getAuthors());
            
            return bookRepository.save(book);
        }
        return null;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorNameContainingIgnoreCase(author);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findAvailableBooks();
    }
}