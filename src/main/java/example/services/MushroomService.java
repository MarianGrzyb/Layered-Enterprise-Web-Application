package example.services;

import example.entities.element.Mushroom;
import example.entities.category.MushroomPicker;

import example.repositories.MushroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MushroomService {
    private final MushroomRepository mushroomRepository;

    public MushroomService(MushroomRepository mushroomRepository) {
        this.mushroomRepository = mushroomRepository;
    }

    public List<Mushroom> findAll() {
        return mushroomRepository.findAll();
    }

    public List<Mushroom> findByPicker(MushroomPicker picker) {
        return mushroomRepository.findByMushroomPicker(picker);
    }

    public Mushroom findById(UUID id) {
        return mushroomRepository.findById(id).orElse(null);
    }

    public Mushroom save(Mushroom mushroom) {
        return mushroomRepository.save(mushroom);
    }

    public void deleteById(UUID id) {
        mushroomRepository.deleteById(id);
    }
}

