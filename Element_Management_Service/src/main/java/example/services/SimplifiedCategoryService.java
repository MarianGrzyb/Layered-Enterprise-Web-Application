package example.services;

import example.dto.simplified.category.SimplifiedCategoryDTO;
import example.entities.element.Mushroom;
import example.entities.simplified.category.SimplifiedCategory;
import example.repositories.SimplifiedCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SimplifiedCategoryService {

    private final SimplifiedCategoryRepository repository;

    public SimplifiedCategoryService(SimplifiedCategoryRepository repository) {
        this.repository = repository;
    }

    public List<SimplifiedCategory> findAll() {
        return repository.findAll();
    }

    public SimplifiedCategory save(SimplifiedCategory cat) {
        return repository.save(cat);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
