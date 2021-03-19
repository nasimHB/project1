package dev.nasim.daos;

import dev.nasim.entities.Employee;
import dev.nasim.utils.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDaoPostgres implements EmployeeDao{

    @Override
    public Set<Employee> getAllEmployees() {
        Set<Employee> employees = new HashSet<Employee>();
        try (Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from employee";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // while book exists, create a new book and store the info
            // into the new book
            while(rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setUsername(rs.getString("username"));
                employee.setPswrd(rs.getString("pswrd"));
                employees.add(employee);
            }
            return employees;
        } catch (SQLException e){
            e.printStackTrace();
            return employees;
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        try (Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from employee where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Employee employee = new Employee();
            employee.setEmployeeId(rs.getInt("employee_id"));
            employee.setFirstName(rs.getString("first_name"));
            employee.setLastName(rs.getString("last_name"));
            employee.setUsername(rs.getString("username"));
            employee.setPswrd(rs.getString("pswrd"));
            return employee;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


}
