package entities;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MushroomDTO implements Comparable<MushroomDTO> {

    private int id;
    private String name;
    private String toxic; //values in: 'Not toxic', 'Slightly toxic', 'Highly toxic'
    private String mushroomPickerSurname;

    @Override
    public boolean equals(Object other) {

        if (this == other){
            return true;
        }

        if (other == null || getClass() != other.getClass()){
            return false;
        }

        MushroomDTO mushroom = (MushroomDTO) other;

        if (!Objects.equals(id, mushroom.id)) {
            return false;
        }

        if (!Objects.equals(name, mushroom.name)) {
            return false;
        }

        if (!Objects.equals(toxic, mushroom.toxic)) {
            return false;
        }

        if (!Objects.equals(mushroomPickerSurname, mushroom.mushroomPickerSurname)) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        return Objects.hash(id, name, toxic, mushroomPickerSurname);
    }

    @Override
    public int compareTo(MushroomDTO other) {

        int ret = Integer.compare(this.id, other.id);

        if (ret == 0) {
            ret = name.compareTo(other.name);
        }

        if (ret == 0) {
            ret = toxic.compareTo(other.toxic);
        }

        if (ret == 0) {
            ret = mushroomPickerSurname.compareTo(other.mushroomPickerSurname);
        }

        return ret;
    }

    @Override
    public String toString() {

        return "Mushroom{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", toxic='" + toxic + "'" +
                ", Mushroom Picker's surname='" + mushroomPickerSurname + "'}";
    }
}
