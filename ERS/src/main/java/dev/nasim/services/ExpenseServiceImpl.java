package dev.nasim.services;

import dev.nasim.daos.ExpenseDao;
import dev.nasim.entities.Expense;
import dev.nasim.exception.BadFormatException;
import dev.nasim.exception.ExpenseNotFoundException;

import java.util.Set;

public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseDao edao;

    public ExpenseServiceImpl(ExpenseDao edao){
        this.edao = edao;
    }


    @Override
    public Expense createExpense(Expense expense) {
        long currentTime = System.currentTimeMillis();
        String status = "pending";

        expense.setDateSubmitted(currentTime);
        expense.setStatus(status);

        expense = edao.createExpense(expense);
        if (expense == null){
            throw new BadFormatException();
        }
        return expense;
    }


    @Override
    public Expense getExpenseById(int expenseId) {
        Expense e = edao.getExpenseById(expenseId);
        if (e == null){
            throw new ExpenseNotFoundException(expenseId);
        }
        return e;
    }


    @Override
    public Set<Expense> getAllExpenses() {
        return edao.getAllExpenses();
    }


    @Override
    public Expense approveExpense(int expenseId, int managerId, String reason) {
        Expense expense = edao.getExpenseById(expenseId);
        long currentTime = System.currentTimeMillis();

        expense.setDateReviewed(currentTime);
        expense.setManagerId(managerId);
        expense.setManagerReason(reason);
        expense.setStatus("approved");

        Expense result = edao.updateExpense(expense);
        if (result == null){
            throw new BadFormatException() ;
        }

        return result;
    }


    @Override
    public Expense denyExpense(int expenseId, int managerId, String reason) {
        Expense expense = edao.getExpenseById(expenseId);
        long currentTime = System.currentTimeMillis();

        expense.setDateReviewed(currentTime);
        expense.setManagerId(managerId);
        expense.setManagerReason(reason);
        expense.setStatus("denied");

        Expense result = edao.updateExpense(expense);
        if (result == null){
            throw new BadFormatException() ;
        }

        return result;
    }


    @Override
    public boolean deleteExpense(int expenseId) {
        Expense e = edao.getExpenseById(expenseId);
        if (e == null){
            throw new ExpenseNotFoundException(expenseId);
        }
        return edao.deleteExpenseById(expenseId);
    }

}
