package example.dto.element;

import lombok.*;

import java.util.UUID;

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
    private UUID mushroomPickerId;
}

