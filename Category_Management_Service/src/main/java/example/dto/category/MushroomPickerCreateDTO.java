package example.dto.category;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class MushroomPickerCreateDTO {
    private String name;
    private String surname;
}


