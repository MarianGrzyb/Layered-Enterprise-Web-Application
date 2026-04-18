package example.repositories;

import example.entities.simplified.category.SimplifiedCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SimplifiedCategoryRepository extends JpaRepository<SimplifiedCategory, UUID> {}
