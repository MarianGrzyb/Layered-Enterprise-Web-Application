package example.dto.element;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class MushroomCreateDTO {
    private String name;
    private String toxic;
}

