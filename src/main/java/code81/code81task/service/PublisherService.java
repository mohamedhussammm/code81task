package code81.code81task.service;

import code81.code81task.model.Publisher;
import code81.code81task.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> getPublisherById(Long id) {
        return publisherRepository.findById(id);
    }

    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Publisher updatePublisher(Long id, Publisher publisherDetails) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (publisherOptional.isPresent()) {
            Publisher publisher = publisherOptional.get();
            publisher.setName(publisherDetails.getName());
            publisher.setAddress(publisherDetails.getAddress());
            publisher.setContactInfo(publisherDetails.getContactInfo());
            return publisherRepository.save(publisher);
        }
        return null;
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

    public List<Publisher> searchPublishers(String name) {
        return publisherRepository.findByNameContainingIgnoreCase(name);
    }
}
