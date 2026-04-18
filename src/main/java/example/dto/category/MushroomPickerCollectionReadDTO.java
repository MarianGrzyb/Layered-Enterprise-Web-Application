package example.dto.category;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class MushroomPickerCollectionReadDTO {
    private UUID id;
    private String surname;

    @Override
    public String toString() {
        return "Mushroom Picker{" +
                "id=" + id +
                ", surname='" + surname + "'}";
    }
}


