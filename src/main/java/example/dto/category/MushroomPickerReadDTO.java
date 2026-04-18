package example.dto.category;

import example.dto.element.MushroomCollectionReadDTO;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class MushroomPickerReadDTO {

    private UUID id;

    private String name;

    private String surname;

    private List<MushroomCollectionReadDTO> mushrooms;

    @Override
    public String toString() {
        return "Mushroom Picker{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", surname='" + surname + "'}";
    }
}


