package example.runners;

import example.entities.Mushroom;
import example.entities.MushroomPicker;
import example.services.MushroomPickerService;
import example.services.MushroomService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.Scanner;

@Order(2)
@Component
public class ConsoleRunner implements CommandLineRunner {

    private final MushroomService mushroomService;
    private final MushroomPickerService pickerService;

    public ConsoleRunner(MushroomService mushroomService, MushroomPickerService pickerService) {
        this.mushroomService = mushroomService;
        this.pickerService = pickerService;
    }

    @Override
    public void run(String... args) {

        Scanner scanner = new Scanner(System.in);
        boolean applicationIsRunning = true;

        while (applicationIsRunning) {
            System.out.println("\nEnter appropriate command (help to list available commands):");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "help":
                    System.out.println("""
                            Available commands:
                            1. help - list available commands
                            2. show-cat - list all categories (Mushroom Pickers)
                            3. show-elems - list all elements (Mushrooms)
                            4. add-elem - add a new element (Mushroom)
                            5. delete-elem - delete an existing element (Mushroom) by ID
                            6. query-by-cat - query elements (Mushrooms) by associated category
                            7. quit - stop the application
                            """);
                    break;

                case "show-cat":
                    List<MushroomPicker> pickers = pickerService.findAll();
                    pickers.forEach(System.out::println);
                    break;

                case "show-elems":
                    List<Mushroom> mushrooms = mushroomService.findAll();
                    mushrooms.forEach(System.out::println);
                    break;

                case "add-elem":
                    System.out.println("Enter element (Mushroom) name:");
                    String name = scanner.nextLine();

                    System.out.println("Enter toxicity, preferably from (Not toxic / Slightly toxic / Highly toxic):");
                    String toxic = scanner.nextLine();

                    System.out.println("Available Mushroom pickers:");
                    pickerService.findAll().forEach(p -> System.out.println(p.getId() + " - " + p.getName()));
                    System.out.println("Enter picker ID:");
                    UUID pickerId = UUID.fromString(scanner.nextLine());

                    MushroomPicker picker = pickerService.findById(pickerId);
                    if (picker == null) {
                        System.out.println("Invalid picker ID!");
                        break;
                    }

                    Mushroom mushroom = Mushroom.builder()
                            .id(UUID.randomUUID())
                            .name(name)
                            .toxic(toxic)
                            .mushroomPicker(picker)
                            .build();

                    mushroomService.save(mushroom);
                    System.out.println("New element (Mushroom) added successfully!");
                    break;

                case "delete-elem":
                    System.out.println("Available Mushroom pickers:");
                    mushroomService.findAll().forEach(p -> System.out.println(p.getId() + " - " + p.getName()));
                    System.out.println("Enter element (Mushroom) ID to delete:");
                    UUID id = UUID.fromString(scanner.nextLine());
                    mushroomService.deleteById(id);
                    System.out.println("Existing element (Mushroom) deleted successfully!");
                    break;

                case "query-by-cat":
                    System.out.println("Available Mushroom pickers:");
                    pickerService.findAll()
                            .forEach(p -> System.out.println(p.getId() + " - " + p.getName() + " " + p.getSurname()));

                    System.out.println("Enter picker ID:");
                    pickerId = UUID.fromString(scanner.nextLine());

                    picker = pickerService.findById(pickerId);
                    if (picker == null) {
                        System.out.println("Invalid picker ID!");
                        break;
                    }

                    List<Mushroom> mushroomsByPicker = mushroomService.findByPicker(picker);

                    if (mushroomsByPicker.isEmpty()) {
                        System.out.println("No mushrooms found for this Mushroom picker.");
                    } else {
                        mushroomsByPicker.forEach(System.out::println);
                    }
                    break;

                case "quit":
                    applicationIsRunning = false;
                    System.out.println("Application closing...");
                    break;

                default:
                    System.out.println("Unknown command. Type 'help' to list all the available commands.");
            }
        }
    }
}

