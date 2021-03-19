package dev.nasim.daos;

import dev.nasim.entities.Expense;

import java.util.Set;

public interface ExpenseDao {

    public Expense createExpense(Expense expense);

    public Expense getExpenseById(int expenseId);
    public Set<Expense> getAllExpenses();

    public Expense updateExpense(Expense expense);

    public boolean deleteExpenseById(int expenseId);
}


