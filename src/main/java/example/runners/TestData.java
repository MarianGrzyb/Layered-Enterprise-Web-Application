package example.runners;

import example.entities.Mushroom;
import example.entities.MushroomPicker;
import example.services.MushroomPickerService;
import example.services.MushroomService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Order(1)
@Component
public class TestData implements CommandLineRunner {
    private final MushroomService mushroomService;
    private final MushroomPickerService mushroomPickerService;

    public TestData(MushroomService mushroomService, MushroomPickerService mushroomPickerService) {
        this.mushroomService = mushroomService;
        this.mushroomPickerService = mushroomPickerService;
    }

    @Override
    public void run(String... args) {
        MushroomPicker p1 = MushroomPicker.builder()
                .id(UUID.randomUUID())
                .name("Marian")
                .surname("Grzyb")
                .build();

        MushroomPicker p2 = MushroomPicker.builder()
                .id(UUID.randomUUID())
                .name("Zuzanna")
                .surname("Ziezia")
                .build();

        MushroomPicker p3 = MushroomPicker.builder()
                .id(UUID.randomUUID())
                .name("Tomasz")
                .surname("Sigma-Kruczalak")
                .build();

        mushroomPickerService.save(p1);
        mushroomPickerService.save(p2);
        mushroomPickerService.save(p3);

        Mushroom m1 = Mushroom.builder()
                .id(UUID.randomUUID())
                .name("Portobello Mushroom")
                .toxic("Not toxic")
                .mushroomPicker(p1)
                .build();

        Mushroom m2 = Mushroom.builder()
                .id(UUID.randomUUID())
                .name("Fly Agaric")
                .toxic("Highly toxic")
                .mushroomPicker(p1)
                .build();

        Mushroom m3 = Mushroom.builder()
                .id(UUID.randomUUID())
                .name("Enoki")
                .toxic("Not toxic")
                .mushroomPicker(p2)
                .build();

        Mushroom m4 = Mushroom.builder()
                .id(UUID.randomUUID())
                .name("Button Mushroom")
                .toxic("Not toxic")
                .mushroomPicker(p2)
                .build();

        Mushroom m5 = Mushroom.builder()
                .id(UUID.randomUUID())
                .name("Bolete")
                .toxic("Not toxic")
                .mushroomPicker(p3)
                .build();

        Mushroom m6 = Mushroom.builder()
                .id(UUID.randomUUID())
                .name("Slippery Jack")
                .toxic("Not toxic")
                .mushroomPicker(p3)
                .build();

        Mushroom m7 = Mushroom.builder()
                .id(UUID.randomUUID())
                .name("Chanterelle")
                .toxic("Not toxic")
                .mushroomPicker(p3)
                .build();

        Mushroom m8 = Mushroom.builder()
                .id(UUID.randomUUID())
                .name("Saffron Milk Cap")
                .toxic("Not toxic")
                .mushroomPicker(p3)
                .build();

        Mushroom m9 = Mushroom.builder()
                .id(UUID.randomUUID())
                .name("Champignon")
                .toxic("Not toxic")
                .mushroomPicker(p3)
                .build();

        Mushroom m10 = Mushroom.builder()
                .id(UUID.randomUUID())
                .name("Wood Blewit")
                .toxic("Not toxic")
                .mushroomPicker(p3)
                .build();

        mushroomService.save(m1);
        mushroomService.save(m2);
        mushroomService.save(m3);
        mushroomService.save(m4);
        mushroomService.save(m5);
        mushroomService.save(m6);
        mushroomService.save(m7);
        mushroomService.save(m8);
        mushroomService.save(m9);
        mushroomService.save(m10);
    }
}

