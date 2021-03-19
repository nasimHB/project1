package dev.nasim.entities;

import java.util.Objects;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String username;
    private String pswrd;

    public Employee(){
        this.employeeId = 0;
        this.firstName = "";
        this.lastName = "";
        this.username = "";
        this.pswrd = "";
    }

    public Employee(int employeeId, String username, String pswrd) {
        this.employeeId = employeeId;
        this.username = username;
        this.pswrd = pswrd;
        this.firstName = "";
        this.lastName = "";

    }

    public Employee(int employeeId, String firstName, String lastName, String username, String pswrd) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.pswrd = pswrd;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPswrd() {
        return pswrd;
    }

    public void setPswrd(String pswrd) {
        this.pswrd = pswrd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return getEmployeeId() == employee.getEmployeeId() && Objects.equals(getFirstName(), employee.getFirstName()) && Objects.equals(getLastName(), employee.getLastName()) && Objects.equals(getUsername(), employee.getUsername()) && Objects.equals(getPswrd(), employee.getPswrd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getFirstName(), getLastName(), getUsername(), getPswrd());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", pswrd='" + pswrd + '\'' +
                '}';
    }


}