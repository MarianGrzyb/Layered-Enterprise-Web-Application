import handlingresults.SharedResult;
import handlingtasks.SharedResource;
import handlingtasks.WorkerThreadInstance;
import utilities.Constants;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int numberOfThreads = handleInitialInput(args);

        SharedResource resource = new SharedResource();
        SharedResult results = new SharedResult();

        ArrayList<WorkerThreadInstance> workers = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            WorkerThreadInstance worker = new WorkerThreadInstance(resource, results);
            Thread thread = new Thread(worker);
            workers.add(worker);
            threads.add(thread);
            thread.start();
        }

        handleFurtherInput(resource, workers, threads);
    }

    private static int handleInitialInput(String[] args) {

        if (args.length == 0) {
            System.out.println("Number of threads not specified!");
            System.exit(1);
        }

        String numberOfThreadsString = args[0];

        try {
            int numberOfThreads = Integer.parseInt(numberOfThreadsString);
            if (numberOfThreads > 0) {
                return numberOfThreads;
            }
            else {
                System.out.println("Number of threads should be a positive number!");
                System.exit(1);
                return 0;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Number of threads should be a number!");
            System.exit(1);
            return 0;
        }
    }

    private static void handleFurtherInput(SharedResource resource, ArrayList<WorkerThreadInstance> workers, ArrayList<Thread> threads) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        String taskInput;

        do {
            System.out.println("Enter the number or quit(quit): ");
            taskInput = scanner.nextLine();

            Integer number = handleTaskInput(taskInput, resource, workers, threads);
            if (number != Constants.NUMBER_NOT_SPECIFIED) {
                resource.put(number);
            }
        } while (!Objects.equals(taskInput, Constants.EXIT_STRING));
    }

    private static Integer handleTaskInput(String taskInput, SharedResource resource, ArrayList<WorkerThreadInstance> workers, ArrayList<Thread> threads) throws InterruptedException {
        try {
            int positiveInteger = Integer.parseInt(taskInput);

            if (positiveInteger > 0) {
                return positiveInteger;
            }
            else {
                System.out.println("Invalid input!");
                return Constants.NUMBER_NOT_SPECIFIED;
            }
        } catch (NumberFormatException ex) {
            if (!Objects.equals(taskInput, Constants.EXIT_STRING)) {
                System.out.println("Invalid input!");
            }
            else {
                System.out.println("Termination process started...");

                for (int i = 0; i < workers.size(); i++) {
                    resource.put(Constants.POISON_PILL);
                }

                for (Thread t : threads) {
                    t.join();
                }
            }
            return Constants.NUMBER_NOT_SPECIFIED;
        }
    }
}