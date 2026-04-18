package employee;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Employee implements Comparable<Employee> {

    private String name;
    private String position;
    private double salary;
    private Set<Employee> subordinates;

    @Override
    public boolean equals(Object other) {

        if (this == other){
            return true;
        }

        if (other == null || getClass() != other.getClass()){
            return false;
        }

        Employee employee = (Employee) other;

        if (!Objects.equals(name, employee.name)) {
            return false;
        }

        if (!Objects.equals(position, employee.position)) {
            return false;
        }

        if (Double.compare(salary, employee.salary) != 0) {
            return false;
        }

        if (!Objects.equals(subordinates, employee.subordinates)) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        return Objects.hash(name, position, salary, subordinates);
    }

    @Override
    public int compareTo(Employee other) {

        int ret = name.compareTo(other.name);

        if (ret == 0) {
            ret = position.compareTo(other.position);
        }

        if (ret == 0) {
            ret = Double.compare(salary, other.salary);
        }

        return ret;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + "'" +
                ", position='" + position + "'" +
                ", salary=" + salary + "}";
    }
}
