package example.dto.element;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class MushroomReadDTO {

    private UUID id;

    private String name;

    private String toxic;

    private UUID mushroomPickerId;

    @Override
    public String toString() {
        return "Mushroom{" +
                "name='" + name + "'" +
                ", toxic='" + toxic + "'" +
                ", Mushroom Picker's Id=" + (mushroomPickerId != null ? mushroomPickerId : "none") +
                '}';
    }
}

