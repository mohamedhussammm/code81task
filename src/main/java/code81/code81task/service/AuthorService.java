package code81.code81task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code81.code81task.model.Author;
import code81.code81task.repository.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author authorDetails) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setFirstName(authorDetails.getFirstName());
            author.setLastName(authorDetails.getLastName());
            author.setBio(authorDetails.getBio());
            author.setBirthDate(authorDetails.getBirthDate());
            return authorRepository.save(author);
        }
        return null;
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

   public List<Author> searchAuthors(String name) {
    return authorRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
}

}
