package entities;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MushroomPicker implements Comparable<MushroomPicker>, Serializable {

    private String name;
    private String surname;
    private ArrayList<Mushroom> mushrooms;

    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        MushroomPicker mushroomPicker = (MushroomPicker) other;

        if (!Objects.equals(name, mushroomPicker.name)) {
            return false;
        }

        if (!Objects.equals(surname, mushroomPicker.surname)) {
            return false;
        }

        List<Integer> ids = mushrooms != null ? mushrooms.stream().map(Mushroom::getId).toList() : null;
        List<Integer> otherIds = mushroomPicker.mushrooms != null ? mushroomPicker.mushrooms.stream().map(Mushroom::getId).toList() : null;

        return Objects.equals(ids, otherIds);
    }

    @Override
    public int hashCode() {
        List<Integer> ids = mushrooms != null ? mushrooms.stream().map(Mushroom::getId).collect(Collectors.toList()) : null;

        return Objects.hash(name, surname, ids);
    }

    @Override
    public int compareTo(MushroomPicker other) {

        int ret = name.compareTo(other.name);

        if (ret == 0) {
            ret = surname.compareTo(other.surname);
        }

        if (ret == 0) {
            List<Integer> ids = mushrooms != null ? mushrooms.stream().map(Mushroom::getId).toList() : List.of();

            List<Integer> otherIds = other.mushrooms != null ? other.mushrooms.stream().map(Mushroom::getId).toList() : List.of();

            int minSize = Math.min(ids.size(), otherIds.size());

            for (int i = 0; i < minSize; i++) {
                int cmp = Integer.compare(ids.get(i), otherIds.get(i));
                if (cmp != 0) {
                    return cmp;
                }
            }

            ret = Integer.compare(ids.size(), otherIds.size());
        }

        return ret;
    }

    @Override
    public String toString() {

        String mushroomIds = mushrooms != null ? mushrooms.stream().map(m -> Integer.toString(m.getId())).collect(Collectors.joining(", ")) : "none";

        return "Mushroom Picker{" +
                "name='" + name + "'" +
                ", surname='" + surname + "'" +
                ", mushrooms=[" + mushroomIds + "]}";
    }
}
