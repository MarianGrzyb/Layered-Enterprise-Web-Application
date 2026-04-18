package entities;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Objects;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Mushroom implements Comparable<Mushroom>, Serializable {

    private int id;
    private String name;
    private String toxic; //values in: 'Not toxic', 'Slightly toxic', 'Highly toxic'
    private MushroomPicker mushroomPicker;

    @Override
    public boolean equals(Object other) {

        if (this == other){
            return true;
        }

        if (other == null || getClass() != other.getClass()){
            return false;
        }

        Mushroom mushroom = (Mushroom) other;

        if (!Objects.equals(id, mushroom.id)) {
            return false;
        }

        if (!Objects.equals(name, mushroom.name)) {
            return false;
        }

        if (!Objects.equals(toxic, mushroom.toxic)) {
            return false;
        }

        String mushroomPickerName = mushroomPicker != null ? mushroomPicker.getName() : null;
        String mushroomPickerSurname = mushroomPicker != null ? mushroomPicker.getSurname() : null;

        String otherMushroomPickerName = mushroom.mushroomPicker != null ? mushroom.mushroomPicker.getName() : null;
        String otherMushroomPickerSurname = mushroom.mushroomPicker != null ? mushroom.mushroomPicker.getSurname() : null;

        if (!Objects.equals(mushroomPickerName, otherMushroomPickerName)) {
            return false;
        }

        if (!Objects.equals(mushroomPickerSurname, otherMushroomPickerSurname)) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        String mushroomPickerName = mushroomPicker != null ? mushroomPicker.getName() : null;
        String mushroomPickerSurname = mushroomPicker != null ? mushroomPicker.getSurname() : null;

        return Objects.hash(id, name, toxic, mushroomPickerName, mushroomPickerSurname);
    }

    @Override
    public int compareTo(Mushroom other) {

        int ret = Integer.compare(this.id, other.id);

        if (ret == 0) {
            ret = name.compareTo(other.name);
        }

        if (ret == 0) {
            ret = toxic.compareTo(other.toxic);
        }

        String mushroomPickerName = mushroomPicker != null ? mushroomPicker.getName() : "null";
        String mushroomPickerSurname = mushroomPicker != null ? mushroomPicker.getSurname() : "null";

        String otherMushroomPickerName = other.mushroomPicker != null ? other.mushroomPicker.getName() : "null";
        String otherMushroomPickerSurname = other.mushroomPicker != null ? other.mushroomPicker.getSurname() : "null";

        if (ret == 0) {
            ret = mushroomPickerName.compareTo(otherMushroomPickerName);
        }

        if (ret == 0) {
            ret = mushroomPickerSurname.compareTo(otherMushroomPickerSurname);
        }

        return ret;
    }

    @Override
    public String toString() {
        String mushroomPickerName = mushroomPicker != null ? mushroomPicker.getName() : "null";
        String mushroomPickerSurname = mushroomPicker != null ? mushroomPicker.getSurname() : "null";

        return "Mushroom{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", toxic='" + toxic + "'" +
                ", Mushroom Picker's name='" + mushroomPickerName + "'" +
                ", Mushroom Picker's surname='" + mushroomPickerSurname + "'}";
    }
}
