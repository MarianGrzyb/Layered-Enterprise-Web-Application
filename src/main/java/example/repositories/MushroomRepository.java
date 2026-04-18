package example.repositories;

import example.entities.element.Mushroom;
import example.entities.category.MushroomPicker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MushroomRepository extends JpaRepository<Mushroom, UUID> {
    List<Mushroom> findByMushroomPicker(MushroomPicker picker);
}


