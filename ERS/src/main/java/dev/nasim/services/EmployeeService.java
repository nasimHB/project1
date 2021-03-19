package dev.nasim.services;

import dev.nasim.entities.Employee;

import java.util.Set;

public interface EmployeeService {

    public Set<Employee> getAllEmployees();

    public Employee getEmployeeById(int employeeId);

    public Employee getEmployeeByUsernameAndPswrd(String username, String pswrd);

}


