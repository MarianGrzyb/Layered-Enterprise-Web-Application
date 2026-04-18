package example.dto.element;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class MushroomCollectionReadDTO {
    private UUID id;
    private String name;

    @Override
    public String toString() {
        return "Mushroom{" +
                "name='" + name + "'}";
    }
}

