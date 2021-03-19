package dev.nasim.entities;

import java.util.Objects;

public class Expense {
    private int expenseId = 0;
    private int amount = 0;
    private String employeeReason = null;
    private String status = "pending";
    private long dateSubmitted = 0;
    private long dateReviewed = 0;
    private String managerReason = null;
    private int employeeId = 0;
    private int managerId = 0;

    public Expense(){

    }

    public Expense(int expenseId, int amount, String employeeReason, int employeeId) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.employeeReason = employeeReason;
        this.employeeId = employeeId;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getEmployeeReason() {
        return employeeReason;
    }

    public void setEmployeeReason(String employeeReason) {
        this.employeeReason = employeeReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(long dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public long getDateReviewed() {
        return dateReviewed;
    }

    public void setDateReviewed(long dateReviewed) {
        this.dateReviewed = dateReviewed;
    }

    public String getManagerReason() {
        return managerReason;
    }

    public void setManagerReason(String managerReason) {
        this.managerReason = managerReason;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return getExpenseId() == expense.getExpenseId() && getAmount() == expense.getAmount() && getDateSubmitted() == expense.getDateSubmitted() && getDateReviewed() == expense.getDateReviewed() && getEmployeeId() == expense.getEmployeeId() && getManagerId() == expense.getManagerId() && Objects.equals(getEmployeeReason(), expense.getEmployeeReason()) && Objects.equals(getStatus(), expense.getStatus()) && Objects.equals(getManagerReason(), expense.getManagerReason());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExpenseId(), getAmount(), getEmployeeReason(), getStatus(), getDateSubmitted(), getDateReviewed(), getManagerReason(), getEmployeeId(), getManagerId());
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", amount=" + amount +
                ", employeeReason='" + employeeReason + '\'' +
                ", status='" + status + '\'' +
                ", dateSubmitted=" + dateSubmitted +
                ", dateReviewed=" + dateReviewed +
                ", managerReason='" + managerReason + '\'' +
                ", employeeId=" + employeeId +
                ", managerId=" + managerId +
                '}';
    }
}