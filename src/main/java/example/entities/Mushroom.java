package example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "mushrooms")
public class Mushroom implements Comparable<example.entities.Mushroom> {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "toxic", nullable = false)
    private String toxic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mushroom_picker_id", nullable = false)
    private MushroomPicker mushroomPicker;

    @Override
    public int compareTo(Mushroom other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return "Mushroom{" +
                "name='" + name + "'" +
                ", toxic='" + toxic + "'" +
                ", Mushroom Picker's Id=" + (mushroomPicker != null ? mushroomPicker.getId() : "none") +
                '}';
    }
}
