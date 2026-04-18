package example.services;

import example.entities.category.MushroomPicker;

import example.repositories.MushroomPickerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MushroomPickerService {
    private final MushroomPickerRepository mushroomPickerRepository;

    public MushroomPickerService(MushroomPickerRepository mushroomPickerRepository) {
        this.mushroomPickerRepository = mushroomPickerRepository;
    }

    public List<MushroomPicker> findAll() {
        return mushroomPickerRepository.findAll();
    }

    public MushroomPicker findById(UUID id) {
        return mushroomPickerRepository.findById(id).orElse(null);
    }

    public MushroomPicker save(MushroomPicker picker) {
        return mushroomPickerRepository.save(picker);
    }

    public void deleteById(UUID id) {
        if (!mushroomPickerRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        mushroomPickerRepository.deleteById(id);
    }
}

