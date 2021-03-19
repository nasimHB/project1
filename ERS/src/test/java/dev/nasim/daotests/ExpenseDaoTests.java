package dev.nasim.daotests;

import dev.nasim.daos.ExpenseDao;
import dev.nasim.daos.ExpenseDaoPostgres;
import dev.nasim.entities.Expense;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Set;

@MockitoSettings(strictness = Strictness.LENIENT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class ExpenseDaoTests {

    //    @Mock
    private ExpenseDao expenseDAO = new ExpenseDaoPostgres();
    private static Expense testExpense = null;


    @Order(1)
    @Test
    void create_expense(){
        this.testExpense = this.expenseDAO.createExpense(new Expense(0, 100, "refund", 1));

        System.out.println(this.testExpense);
        Assertions.assertNotNull(this.testExpense);
        Assertions.assertNotEquals(0, this.testExpense.getExpenseId());
    }

    @Order(2)
    @Test
    void get_expense_by_id(){
        Expense e = this.expenseDAO.getExpenseById(this.testExpense.getExpenseId());

        Assertions.assertNotNull(e);
        Assertions.assertEquals(e.getExpenseId(), this.testExpense.getExpenseId());
        Assertions.assertEquals(e.getAmount(), this.testExpense.getAmount());
    }

    @Order(3)
    @Test
    void get_all_expenses(){
        Set<Expense> expenses = this.expenseDAO.getAllExpenses();
        Assertions.assertTrue(expenses.size() >= 1);
    }

    @Order(4)
    @Test
    void update_expense(){
        this.testExpense.setAmount(150);
        Expense e = this.expenseDAO.updateExpense(this.testExpense);

        Assertions.assertEquals(this.testExpense.getAmount(), e.getAmount());
    }

    @Order(5)
    @Test
    void delete_expense(){
        int testId = this.testExpense.getExpenseId();
        boolean result = this.expenseDAO.deleteExpenseById(testId);
        Assertions.assertTrue(result);
    }
}

