package example.controllers;

import example.entities.category.MushroomPicker;
import example.services.MushroomPickerService;
import example.dto.category.MushroomPickerCreateDTO;
import example.dto.category.MushroomPickerReadDTO;
import example.dto.category.MushroomPickerCollectionReadDTO;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.UUID;

@RestController
@RequestMapping("/mushroomPickers")
public class MushroomPickerController {

    private final MushroomPickerService service;

    public MushroomPickerController(MushroomPickerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MushroomPickerReadDTO> createMushroomPicker(@RequestBody MushroomPickerCreateDTO newMushroomPickerDTO) {

        MushroomPicker newMushroomPicker = new MushroomPicker();
        newMushroomPicker.setId(UUID.randomUUID());
        newMushroomPicker.setName(newMushroomPickerDTO.getName());
        newMushroomPicker.setSurname(newMushroomPickerDTO.getSurname());

        MushroomPicker created = service.save(newMushroomPicker);

        MushroomPickerReadDTO response = new MushroomPickerReadDTO(
                created.getId(),
                created.getName(),
                created.getSurname()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MushroomPickerReadDTO> updateMushroomPicker(@PathVariable UUID id, @RequestBody MushroomPickerCreateDTO dto) {

        MushroomPicker existing = service.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setName(dto.getName());
        existing.setSurname(dto.getSurname());

        MushroomPicker updated = service.save(existing);

        MushroomPickerReadDTO response = new MushroomPickerReadDTO(
                updated.getId(),
                updated.getName(),
                updated.getSurname()
        );

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMushroomPicker(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MushroomPickerReadDTO> getMushroomPickerById(@PathVariable UUID id) {

        MushroomPicker existing = service.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        MushroomPickerReadDTO response = new MushroomPickerReadDTO(
                existing.getId(),
                existing.getName(),
                existing.getSurname()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MushroomPickerCollectionReadDTO>> getAllMushroomPickers() {

        List<MushroomPicker> pickers = service.findAll();

        List<MushroomPickerCollectionReadDTO> response =
                pickers.stream()
                        .map(mushroomPicker -> new MushroomPickerCollectionReadDTO(
                                mushroomPicker.getId(),
                                mushroomPicker.getSurname()))
                        .toList();

        return ResponseEntity.ok(response);
    }
}
