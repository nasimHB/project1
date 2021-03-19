package dev.nasim.servicetests;

import dev.nasim.daos.EmployeeDao;
import dev.nasim.entities.Employee;
import dev.nasim.services.EmployeeService;
import dev.nasim.services.EmployeeServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.HashSet;
import java.util.Set;

@MockitoSettings(strictness = Strictness.LENIENT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    private EmployeeService employeeService;

    @Mock
    private EmployeeDao employeeDao = null;

    @BeforeEach
    void setup(){

        Employee employee = new Employee();
        Mockito.when(this.employeeDao.getEmployeeById(2)).thenReturn(
                new Employee(2, "nhb", "123")
        );

        Set<Employee> employees = new HashSet<>();
        employees.add(
                new Employee(2, "nhb", "123")
        );
        employees.add(
                new Employee(3, "mtr", "123")
        );

        Mockito.when(this.employeeDao.getAllEmployees()).thenReturn(employees);

        employeeService = new EmployeeServiceImpl(employeeDao);
    }

    @Order(1)
    @Test
    void get_employee_by_id(){
        Employee testEmployee = this.employeeService.getEmployeeById(2);

        Assertions.assertNotNull(testEmployee);
        Assertions.assertEquals(2, testEmployee.getEmployeeId());
        System.out.println(testEmployee);
    }

    @Order(2)
    @Test
    void get_all_employees(){
        Set<Employee> employees = this.employeeService.getAllEmployees();

        Assertions.assertNotNull(employees);
        Assertions.assertTrue(employees.size() > 0);
        System.out.println(employees);
    }

    @Order(3)
    @Test
    void get_employee_by_username_and_pswrd(){
        String username = "nhb";
        String pswrd = "123";

        Employee e = this.employeeService.getEmployeeByUsernameAndPswrd(username, pswrd);
        Assertions.assertNotNull(e);
        Assertions.assertEquals("nhb", e.getUsername());
        System.out.println(e);
    }

}
