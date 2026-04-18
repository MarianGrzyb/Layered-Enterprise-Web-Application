import employee.Employee;
import employee.comparator.EmployeeComparator;
import employee.init.TestSet;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String mode = handleInputMode(args);

        display(mode);
    }

    private static String handleInputMode(String[] args) {

        if (args.length == 0) {
            System.out.println("No mode specified!");
            System.exit(1);
        }

        String mode = args[0];

        if (!mode.equals("unsorted") && !mode.equals("natural") && !mode.equals("alternative")) {
            System.out.println("Invalid mode!");
            System.exit(1);
        }

        return mode;
    }

    private static void display(String mode) {
        Set<Employee> testSet = TestSet.createTestSet();

        Set<Employee> sortedSet = chooseSortingMethodSet(mode, testSet);

        Map<Employee, Integer> sortedMap = chooseSortingMethodMap(mode);

        if (sortedSet != null && sortedMap != null) {
            printRecursiveStructure(sortedSet);
            printContentsOfTheMap(sortedMap, testSet);
        }
    }

    private static Set<Employee> chooseSortingMethodSet(String mode, Set<Employee> testSet) {

        Set<Employee> sortedSet;

        switch (mode) {
            case "unsorted":
                sortedSet = new HashSet<>(testSet);
                break;
            case "natural":
                sortedSet = new TreeSet<>(testSet);
                break;
            case "alternative":
                sortedSet = new TreeSet<>(new EmployeeComparator());
                sortedSet.addAll(testSet);
                break;
            default:
                sortedSet = null;
                break;
        }

        return sortedSet;
    }

    private static Map<Employee, Integer> chooseSortingMethodMap(String mode) {

        Map<Employee, Integer> sortedMap;

        switch (mode) {
            case "unsorted":
                sortedMap = new HashMap<>();
                break;
            case "natural":
                sortedMap = new TreeMap<>();
                break;
            case "alternative":
                sortedMap = new TreeMap<>(new EmployeeComparator());
                break;
            default:
                sortedMap = null;
                break;
        }

        return sortedMap;
    }

    private static void printRecursiveStructure(Set<Employee> sortedSet) {

        ArrayList<Employee> subordinates = new ArrayList<>();

        for (Employee e : sortedSet) {
            if (e.getSubordinates() != null) {
                subordinates.addAll(e.getSubordinates());
            }
        }

        System.out.println("\nRecursive Structure:\n");

        for (Employee e : sortedSet) {
            if (!subordinates.contains(e)) {
                determineHierarchy(e, 1, sortedSet);
            }
        }
    }

    private static void determineHierarchy(Employee e, int depth, Set<Employee> sortedSet) {

        String structureMarker = "-".repeat(depth);
        System.out.println(structureMarker + e);

        if (e.getSubordinates() == null || e.getSubordinates().isEmpty()) {
            return;
        }

        Set<Employee> properSet = new HashSet<>();

        if (properSet.getClass() != sortedSet.getClass()) {

            Comparator<? super Employee> comp = ((TreeSet<Employee>) sortedSet).comparator();

            if (comp == null) {
                properSet = new TreeSet<>();
            }
            else {
                properSet = new TreeSet<>(new EmployeeComparator());
            }
        }

        properSet.addAll(e.getSubordinates());

        for (Employee employee : properSet) {
            determineHierarchy(employee, depth + 1, sortedSet);
        }
    }

    private static void printContentsOfTheMap(Map<Employee, Integer> sortedMap, Set<Employee> testSet) {

        for (Employee employee : testSet) {

            int numberOfSubDepartments = 0;
            ArrayList<Employee> allSubordinates = new ArrayList<>();
            allSubordinates.add(employee);

            int index = 0;
            while (index < allSubordinates.size()) {

                Employee element = allSubordinates.get(index);

                if (element.getSubordinates() != null) {
                    allSubordinates.addAll(element.getSubordinates());
                    numberOfSubDepartments += element.getSubordinates().size();
                }

                index++;
            }

            sortedMap.put(employee, numberOfSubDepartments);
        }

        System.out.println("\nContents of the map:\n");

        for (Employee employee : sortedMap.keySet()) {
            System.out.println(employee + " -> " + sortedMap.get(employee));
        }
    }
}
