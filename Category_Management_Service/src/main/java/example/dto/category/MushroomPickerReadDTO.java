package example.dto.category;

import lombok.*;

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

    @Override
    public String toString() {
        return "Mushroom Picker{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", surname='" + surname + "'}";
    }
}


