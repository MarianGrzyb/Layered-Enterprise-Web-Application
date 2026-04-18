package employee.comparator;

import employee.Employee;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee d1, Employee d2) {

        int ret = Double.compare(d2.getSalary(), d1.getSalary());

        if (ret == 0) {
            ret = d1.getPosition().compareTo(d2.getPosition());
        }

        if (ret == 0) {
            ret = d1.getName().compareTo(d2.getName());
        }

        return ret;
    }
}
