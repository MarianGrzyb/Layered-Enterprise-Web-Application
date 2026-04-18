package example.services;

import example.entities.MushroomPicker;

import example.repositories.MushroomPickerRepository;
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

    public void save(MushroomPicker picker) {
        mushroomPickerRepository.save(picker);
    }
}

