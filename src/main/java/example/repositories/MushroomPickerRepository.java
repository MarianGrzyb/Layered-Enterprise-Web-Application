package example.repositories;

import example.entities.MushroomPicker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MushroomPickerRepository extends JpaRepository<MushroomPicker, UUID> {}

