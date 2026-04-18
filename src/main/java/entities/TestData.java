package entities;

import java.util.ArrayList;

public class TestData {

    public static ArrayList<MushroomPicker> initTestData () {

        ArrayList<MushroomPicker> mushroomPickers = new ArrayList<>();

        MushroomPicker p1 = MushroomPicker.builder()
                .name("Marian")
                .surname("Grzyb")
                .mushrooms(new ArrayList<>())
                .build();

        MushroomPicker p2 = MushroomPicker.builder()
                .name("Zuzanna")
                .surname("Ziezia")
                .mushrooms(new ArrayList<>())
                .build();

        MushroomPicker p3 = MushroomPicker.builder()
                .name("Tomasz")
                .surname("Sigma-Kruczalak")
                .mushrooms(new ArrayList<>())
                .build();

        Mushroom m1 = Mushroom.builder()
                .id(1)
                .name("Portobello Mushroom")
                .toxic("Not toxic")
                .mushroomPicker(p1)
                .build();

        Mushroom m2 = Mushroom.builder()
                .id(2)
                .name("Fly Agaric")
                .toxic("Highly toxic")
                .mushroomPicker(p1)
                .build();

        Mushroom m3 = Mushroom.builder()
                .id(3)
                .name("Enoki")
                .toxic("Not toxic")
                .mushroomPicker(p2)
                .build();

        Mushroom m4 = Mushroom.builder()
                .id(4)
                .name("Button Mushroom")
                .toxic("Not toxic")
                .mushroomPicker(p2)
                .build();

        Mushroom m5 = Mushroom.builder()
                .id(5)
                .name("Bolete")
                .toxic("Not toxic")
                .mushroomPicker(p3)
                .build();

        Mushroom m6 = Mushroom.builder()
                .id(6)
                .name("Slippery Jack")
                .toxic("Not toxic")
                .mushroomPicker(p3)
                .build();

        Mushroom m7 = Mushroom.builder()
                .id(7)
                .name("Chanterelle")
                .toxic("Not toxic")
                .mushroomPicker(p3)
                .build();

        Mushroom m8 = Mushroom.builder()
                .id(8)
                .name("Saffron Milk Cap")
                .toxic("Not toxic")
                .mushroomPicker(p3)
                .build();

        Mushroom m9 = Mushroom.builder()
                .id(9)
                .name("Champignon")
                .toxic("Not toxic")
                .mushroomPicker(p3)
                .build();

        Mushroom m10 = Mushroom.builder()
                .id(10)
                .name("Wood Blewit")
                .toxic("Not toxic")
                .mushroomPicker(p3)
                .build();

        p1.getMushrooms().add(m1);
        p1.getMushrooms().add(m2);

        p2.getMushrooms().add(m3);
        p2.getMushrooms().add(m4);

        p3.getMushrooms().add(m5);
        p3.getMushrooms().add(m6);
        p3.getMushrooms().add(m7);
        p3.getMushrooms().add(m8);
        p3.getMushrooms().add(m9);
        p3.getMushrooms().add(m10);

        mushroomPickers.add(p1);
        mushroomPickers.add(p2);
        mushroomPickers.add(p3);

        return mushroomPickers;
    }
}
