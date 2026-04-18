import entities.MushroomPicker;
import entities.Mushroom;
import entities.MushroomDTO;
import entities.TestData;
import utilities.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.io.*;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        ArrayList<MushroomPicker> mushroomPickers = TestData.initTestData();

        originalOrder(mushroomPickers);
        mushroomSet(mushroomPickers);
        serialization(mushroomPickers);
        parallelPipelines(mushroomPickers);
    }

    public static void originalOrder(ArrayList<MushroomPicker> mushroomPickers) {
        System.out.println("\nORIGINAL ORDER:\n");

        mushroomPickers.forEach(mushroomPicker -> {
            System.out.println(mushroomPicker);
            mushroomPicker.getMushrooms().forEach(mushroom -> System.out.println("-" + mushroom));
        });
    }

    public static void mushroomSet(ArrayList<MushroomPicker> mushroomPickers) {
        System.out.println("\nMUSHROOM SET:\n");

        Set<Mushroom> allMushrooms = mushroomPickers.stream()
                .flatMap(mushroomPicker -> mushroomPicker.getMushrooms().stream())
                .collect(Collectors.toSet());

        allMushrooms.stream().forEach(System.out::println);

        filteredMushroomSet(allMushrooms);
        sortedMushroomDTOList(allMushrooms);
    }

    public static void filteredMushroomSet(Set<Mushroom> allMushrooms) {
        System.out.println("\nFILTERED MUSHROOM SET:\n");

        allMushrooms.stream()
                .filter((mushroom -> mushroom.getToxic().equals("Not toxic")))
                .sorted((m1, m2) -> m1.getName().compareTo(m2.getName()))
                .forEach(System.out::println);
    }

    public static void sortedMushroomDTOList(Set<Mushroom> allMushrooms) {
        System.out.println("\nSORTED MUSHROOM DTO LIST:\n");

        List<MushroomDTO> mushroomDTOList = allMushrooms.stream()
        .map(m -> MushroomDTO.builder()
                .id(m.getId())
                .name(m.getName())
                .toxic(m.getToxic())
                .mushroomPickerSurname(m.getMushroomPicker().getSurname())
                .build())
                .sorted()
                .toList();
        mushroomDTOList.forEach(System.out::println);
    }

    public static void serialization(ArrayList<MushroomPicker> mushroomPickers) {
        System.out.println("\nSERIALIZATION:\n");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("binaryFile"))) {
            oos.writeObject(mushroomPickers);
        } catch (IOException ex) {
            System.out.println("IOException occurred");
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("binaryFile"))) {
            ArrayList<MushroomPicker> deserializedMushroomPickers = (ArrayList<MushroomPicker>) ois.readObject();
            deserializedMushroomPickers.forEach(mushroomPicker -> {
                System.out.println(mushroomPicker);
                mushroomPicker.getMushrooms().forEach(m -> System.out.println("-" + m));
            });
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("IOException occurred");
        }
    }

    public static void parallelPipelines(ArrayList<MushroomPicker> mushroomPickers) {
        System.out.println("\nPARALLEL STREAM PIPELINES:\n");

        ForkJoinPool pool = new ForkJoinPool(Constants.NUMBER_OF_THREADS);

        try {
            pool.submit(() -> mushroomPickers.stream()
                    .parallel()
                    .forEach(mushroomPicker -> mushroomPicker.getMushrooms().forEach(mushroom -> {
                        try {
                            Thread.sleep(Constants.WAITING_TIME);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        System.out.println("- " + mushroom);
                    }))).get();
        } catch (InterruptedException | ExecutionException ex) {
            System.out.println("Exception occurred");
        } finally {
        pool.shutdown();
        }
    }
}