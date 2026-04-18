package example.entities.simplified.category;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class SimplifiedCategory {

    @Id
    private UUID id;

    private String name;

    private String surname;
}

