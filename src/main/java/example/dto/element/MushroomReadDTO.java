package example.dto.element;

import example.dto.category.MushroomPickerCollectionReadDTO;
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

    private MushroomPickerCollectionReadDTO mushroomPicker;

    @Override
    public String toString() {
        return "Mushroom{" +
                "name='" + name + "'" +
                ", toxic='" + toxic + "'" +
                ", Mushroom Picker's Id=" + (mushroomPicker != null ? mushroomPicker.getId() : "none") +
                '}';
    }
}

