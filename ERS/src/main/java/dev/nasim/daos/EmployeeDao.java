package dev.nasim.daos;

import dev.nasim.entities.Employee;

import java.util.Set;

public interface EmployeeDao {

    public Set<Employee> getAllEmployees();
    public Employee getEmployeeById(int employeeId);
}


