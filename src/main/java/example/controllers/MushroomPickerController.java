package example.controllers;

import example.entities.category.MushroomPicker;
import example.services.MushroomPickerService;
import example.dto.category.MushroomPickerCreateDTO;
import example.dto.category.MushroomPickerReadDTO;
import example.dto.category.MushroomPickerCollectionReadDTO;
import example.entities.element.Mushroom;
import example.dto.element.MushroomCollectionReadDTO;
import example.dto.element.MushroomCreateDTO;

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

        List<MushroomCollectionReadDTO> mushroomDTOs =
                created.getMushrooms() == null
                        ? List.of()
                        : created.getMushrooms().stream()
                        .map(mushroom -> new MushroomCollectionReadDTO(
                                mushroom.getId(),
                                mushroom.getName()))
                        .toList();

        MushroomPickerReadDTO response = new MushroomPickerReadDTO(
                created.getId(),
                created.getName(),
                created.getSurname(),
                mushroomDTOs
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

        List<MushroomCollectionReadDTO> mushroomDTOs =
                updated.getMushrooms() == null
                        ? List.of()
                        : updated.getMushrooms().stream()
                        .map(mushroom -> new MushroomCollectionReadDTO(
                                mushroom.getId(),
                                mushroom.getName()))
                        .toList();

        MushroomPickerReadDTO response = new MushroomPickerReadDTO(
                updated.getId(),
                updated.getName(),
                updated.getSurname(),
                mushroomDTOs
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

        List<MushroomCollectionReadDTO> mushroomDTOs =
                existing.getMushrooms() == null
                        ? List.of()
                        : existing.getMushrooms().stream()
                        .map(mushroom -> new MushroomCollectionReadDTO(
                                mushroom.getId(),
                                mushroom.getName()))
                        .toList();

        MushroomPickerReadDTO response = new MushroomPickerReadDTO(
                existing.getId(),
                existing.getName(),
                existing.getSurname(),
                mushroomDTOs
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

    @GetMapping("/{pickerId}/mushrooms")
    public ResponseEntity<List<MushroomCollectionReadDTO>> getMushroomsByPicker(@PathVariable UUID pickerId) {

        MushroomPicker mushroomPicker = service.findById(pickerId);
        if (mushroomPicker == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<MushroomCollectionReadDTO> mushrooms =
                mushroomPicker.getMushrooms() == null
                        ? List.of()
                        : mushroomPicker.getMushrooms().stream()
                        .map(mushroom -> new MushroomCollectionReadDTO(
                                mushroom.getId(),
                                mushroom.getName()))
                        .toList();

        return ResponseEntity.ok(mushrooms);
    }

    @PostMapping("/{pickerId}/mushrooms")
    public ResponseEntity<MushroomCollectionReadDTO> addMushroomToPicker(@PathVariable UUID pickerId, @RequestBody MushroomCreateDTO newMushroomDTO) {

        MushroomPicker mushroomPicker = service.findById(pickerId);
        if (mushroomPicker == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Mushroom mushroom = new Mushroom();
        mushroom.setId(UUID.randomUUID());
        mushroom.setName(newMushroomDTO.getName());
        mushroom.setToxic(newMushroomDTO.getToxic());
        mushroom.setMushroomPicker(mushroomPicker);

        if (mushroomPicker.getMushrooms() != null) {
            mushroomPicker.getMushrooms().add(mushroom);
        } else {
            mushroomPicker.setMushrooms(new ArrayList<>(List.of(mushroom)));
        }

        service.save(mushroomPicker);

        MushroomCollectionReadDTO responseDTO = new MushroomCollectionReadDTO(
                mushroom.getId(),
                mushroom.getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
