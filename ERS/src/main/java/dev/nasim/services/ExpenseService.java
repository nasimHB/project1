package dev.nasim.services;

import dev.nasim.entities.Expense;

import java.util.Set;

public interface ExpenseService {

    public Expense createExpense(Expense expense);

    public Expense getExpenseById(int expenseId);
    public Set<Expense> getAllExpenses();

    public Expense approveExpense(int expenseId, int managerId, String reason);
    public Expense denyExpense(int expenseId, int managerId, String reason);

    public boolean deleteExpense(int expenseId);
}
