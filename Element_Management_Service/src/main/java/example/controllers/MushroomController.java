package example.controllers;

import example.entities.element.Mushroom;
import example.services.MushroomService;
import example.dto.element.MushroomCreateDTO;
import example.dto.element.MushroomReadDTO;
import example.dto.element.MushroomCollectionReadDTO;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.UUID;

@RestController
@RequestMapping("/mushrooms")
public class MushroomController {

    private final MushroomService service;

    public MushroomController(MushroomService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MushroomReadDTO> createMushroom(@RequestBody MushroomCreateDTO newMushroomDTO) {

        Mushroom newMushroom = new Mushroom();
        newMushroom.setId(UUID.randomUUID());
        newMushroom.setName(newMushroomDTO.getName());
        newMushroom.setToxic(newMushroomDTO.getToxic());
        newMushroom.setMushroomPickerId(newMushroomDTO.getMushroomPickerId());

        Mushroom created = service.save(newMushroom);

        MushroomReadDTO response = new MushroomReadDTO(
                created.getId(),
                created.getName(),
                created.getToxic(),
                created.getMushroomPickerId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MushroomReadDTO> updateMushroom(@PathVariable UUID id, @RequestBody MushroomCreateDTO dto) {

        Mushroom existing = service.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setName(dto.getName());
        existing.setToxic(dto.getToxic());

        Mushroom updated = service.save(existing);

        MushroomReadDTO response = new MushroomReadDTO(
                updated.getId(),
                updated.getName(),
                updated.getToxic(),
                updated.getMushroomPickerId()
        );

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMushroom(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MushroomReadDTO> getMushroomById(@PathVariable UUID id) {

        Mushroom existing = service.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        MushroomReadDTO response = new MushroomReadDTO(
                existing.getId(),
                existing.getName(),
                existing.getToxic(),
                existing.getMushroomPickerId()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MushroomCollectionReadDTO>> getAllMushrooms() {

        List<Mushroom> mushrooms = service.findAll();

        List<MushroomCollectionReadDTO> response =
                mushrooms.stream()
                        .map(p -> new MushroomCollectionReadDTO(
                                p.getId(),
                                p.getName()))
                        .toList();

        return ResponseEntity.ok(response);
    }
}

