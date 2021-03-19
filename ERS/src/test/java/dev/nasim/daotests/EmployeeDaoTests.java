package dev.nasim.daotests;

import dev.nasim.daos.EmployeeDao;
import dev.nasim.daos.EmployeeDaoPostgres;
import dev.nasim.entities.Employee;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTests {
    private EmployeeDao employeeDAO = new EmployeeDaoPostgres();


    @Order(1)
    @Test
    void get_employee_by_id(){
        Employee e = this.employeeDAO.getEmployeeById(1);
        Assertions.assertEquals(e.getUsername(), "jessie1");
        Assertions.assertEquals(e.getEmployeeId(), 1);
    }

    @Order(2)
    @Test
    void get_all_employees(){
        Set<Employee> employees = this.employeeDAO.getAllEmployees();
        System.out.println(employees);
        Assertions.assertTrue(employees.size() >= 1);
    }


}

