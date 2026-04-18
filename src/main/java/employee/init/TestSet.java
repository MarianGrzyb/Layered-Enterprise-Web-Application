package employee.init;

import employee.Employee;

import java.util.Set;
import java.util.HashSet;

public class TestSet {
    public static Set<Employee> createTestSet() {

        Employee d7 = new Employee("Seven", "Seventh", 7.0, null);
        Employee d8 = new Employee("Eight", "Eighth", 8.0, null);
        Employee d9 = new Employee("Nine", "Ninth", 9.0, null);
        Employee d10 = new Employee("Ten", "Tenth", 10.0, null);

        Set<Employee> subordinatesD4 = new HashSet<>();
        subordinatesD4.add(d7);
        subordinatesD4.add(d8);

        Set<Employee> subordinatesD5 = new HashSet<>();
        subordinatesD5.add(d9);

        Set<Employee> subordinatesD6 = new HashSet<>();
        subordinatesD6.add(d10);

        Employee d4 = new Employee("Four", "Fourth", 4.0, subordinatesD4);
        Employee d5 = new Employee("Five", "Fifth", 5.0, subordinatesD5);
        Employee d6 = new Employee("Six", "Sixth", 6.0, subordinatesD6);

        Set<Employee> subordinatesD1 = new HashSet<>();
        subordinatesD1.add(d4);

        Set<Employee> subordinatesD2 = new HashSet<>();
        subordinatesD2.add(d5);

        Set<Employee> subordinatesD3 = new HashSet<>();
        subordinatesD3.add(d6);

        Employee d1 = new Employee("One", "First", 1.0, subordinatesD1);
        Employee d2 = new Employee("Two", "Second", 2.0, subordinatesD2);
        Employee d3 = new Employee("Three", "Third", 3.0, subordinatesD3);

        Set<Employee> testSet = new HashSet<>();
        testSet.add(d1);
        testSet.add(d2);
        testSet.add(d3);
        testSet.add(d4);
        testSet.add(d5);
        testSet.add(d6);
        testSet.add(d7);
        testSet.add(d8);
        testSet.add(d9);
        testSet.add(d10);

        return testSet;
    }
}
