package example.services;

import example.entities.element.Mushroom;

import example.repositories.MushroomRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public List<Mushroom> findByPickerId(UUID pickerId) {
        return mushroomRepository.findByMushroomPickerId(pickerId);
    }

    public void deleteByPickerId(UUID pickerId) {
        mushroomRepository.deleteAll(mushroomRepository.findByMushroomPickerId(pickerId));
    }

    public Mushroom findById(UUID id) {
        return mushroomRepository.findById(id).orElse(null);
    }

    public Mushroom save(Mushroom mushroom) {
        return mushroomRepository.save(mushroom);
    }

    public void deleteById(UUID id) {
        if (!mushroomRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        mushroomRepository.deleteById(id);
    }
}

