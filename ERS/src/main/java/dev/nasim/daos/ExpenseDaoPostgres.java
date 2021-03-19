package dev.nasim.daos;

import dev.nasim.entities.Expense;
import dev.nasim.utils.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ExpenseDaoPostgres implements ExpenseDao{
    @Override
    public Expense createExpense(Expense expense) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into expense (amount, employee_reason, status, date_submitted, employee_id) values (?, ?, ?, ?, ?);";
            // anything that requires you to write precise string is immediate very likely
            // to cause bugs
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, expense.getAmount());
            ps.setString(2, expense.getEmployeeReason());
            ps.setString(3, expense.getStatus());
            ps.setLong(4, expense.getDateSubmitted());
            ps.setInt(5, expense.getEmployeeId());

            ps.execute(); // execute the sql statement

            // result set is a cursor that starts before the actual 1st element
            ResultSet rs = ps.getGeneratedKeys(); // return the value of the generated keys
            rs.next();
            int key = rs.getInt("expense_id");
            expense.setExpenseId(key);

            return expense;

        } catch (SQLException sqlException){
            return null;
        }
    }

    @Override
    public Expense getExpenseById(int expenseId) {
        try (Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from expense where expense_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expenseId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Expense expense = new Expense();
            expense.setExpenseId(rs.getInt("expense_id"));
            expense.setAmount(rs.getInt("amount"));
            expense.setEmployeeReason(rs.getString("employee_reason"));
            expense.setStatus(rs.getString("status"));
            expense.setDateSubmitted(rs.getLong("date_submitted"));
            expense.setDateReviewed(rs.getLong("date_reviewed"));
            expense.setManagerReason(rs.getString("manager_reason"));
            expense.setEmployeeId(rs.getInt("employee_id"));
            expense.setManagerId(rs.getInt("manager_id"));
            return expense;
        } catch (SQLException e){
            return null;
        }
    }

    @Override
    public Set<Expense> getAllExpenses() {
        Set<Expense> expenses = new HashSet<Expense>();
        try (Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from expense";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // while book exists, create a new book and store the info
            // into the new book
            while(rs.next()) {
                Expense expense = new Expense();
                expense.setExpenseId(rs.getInt("expense_id"));
                expense.setAmount(rs.getInt("amount"));
                expense.setEmployeeReason(rs.getString("employee_reason"));
                expense.setStatus(rs.getString("status"));
                expense.setDateSubmitted(rs.getLong("date_submitted"));
                expense.setDateReviewed(rs.getLong("date_reviewed"));
                expense.setManagerReason(rs.getString("manager_reason"));
                expense.setEmployeeId(rs.getInt("employee_id"));
                expense.setManagerId(rs.getInt("manager_id"));
                expenses.add(expense);
            }
            return expenses;
        } catch (SQLException e){
            e.printStackTrace();
            return expenses;
        }
    }

    @Override
    public Expense updateExpense(Expense expense) {
        try (Connection conn = ConnectionUtil.createConnection()){
            String sql = "update expense set amount = ?, employee_reason = ?, status = ?, date_submitted = ?, date_reviewed = ?, manager_reason = ?, employee_id = ?, manager_id = ? where expense_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expense.getAmount());
            ps.setString(2, expense.getEmployeeReason());
            ps.setString(3, expense.getStatus());
            ps.setLong(4, expense.getDateSubmitted());
            ps.setLong(5, expense.getDateReviewed());
            ps.setString(6, expense.getManagerReason());
            ps.setInt(7, expense.getEmployeeId());
            ps.setInt(8, expense.getManagerId());
            ps.setInt(9, expense.getExpenseId());
            ps.execute();
            return expense;

        } catch (SQLException e){
            return null;
        }
    }

    @Override
    public boolean deleteExpenseById(int expenseId) {
        try (Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from expense where expense_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expenseId);
            ps.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}