package example.dto.simplified.category;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedCategoryDTO {
    private UUID id;
    private String name;
    private String surname;
}

