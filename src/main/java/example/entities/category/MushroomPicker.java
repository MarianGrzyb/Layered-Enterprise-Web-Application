package example.entities.category;

import example.entities.element.Mushroom;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "mushroom_pickers")
public class MushroomPicker {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToMany(
            mappedBy = "mushroomPicker",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Mushroom> mushrooms;

    @Override
    public String toString() {
        return "Mushroom Picker{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", surname='" + surname + "'}";
    }
}

