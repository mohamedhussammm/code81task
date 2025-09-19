package code81.code81task.service;

import code81.code81task.model.Category;
import code81.code81task.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setName(categoryDetails.getName());
            category.setDescription(categoryDetails.getDescription());
            category.setParent(categoryDetails.getParent());
            return categoryRepository.save(category);
        }
        return null;
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> getTopLevelCategories() {
        return categoryRepository.findByParentIsNull();
    }

    public List<Category> searchCategories(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }
}
