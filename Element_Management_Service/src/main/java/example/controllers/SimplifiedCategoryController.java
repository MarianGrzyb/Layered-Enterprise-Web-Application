package example.controllers;

import example.dto.simplified.category.SimplifiedCategoryDTO;
import example.entities.simplified.category.SimplifiedCategory;
import example.services.SimplifiedCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class SimplifiedCategoryController {

    private final SimplifiedCategoryService service;

    public SimplifiedCategoryController(SimplifiedCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<SimplifiedCategoryDTO> getAllCategories() {
        return service.findAll().stream()
                .map(c -> new SimplifiedCategoryDTO(c.getId(), c.getName(), c.getSurname()))
                .toList();
    }

    @PostMapping
    public void createCategory(@RequestBody SimplifiedCategoryDTO dto) {
        service.save(new SimplifiedCategory(dto.getId(), dto.getName(), dto.getSurname()));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable UUID id) {
        service.deleteById(id);
    }
}
