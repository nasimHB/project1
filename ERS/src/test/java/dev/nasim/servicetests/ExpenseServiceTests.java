package dev.nasim.servicetests;

import dev.nasim.daos.ExpenseDao;
import dev.nasim.entities.Expense;
import dev.nasim.services.ExpenseService;
import dev.nasim.services.ExpenseServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;

import java.util.HashSet;
import java.util.Set;

@MockitoSettings(strictness = Strictness.LENIENT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTests {

    private ExpenseService expenseService;
    private static Expense testExpense = null;

    @Mock
    private ExpenseDao expenseDAO;

    @BeforeEach
    void setup(){

        // create expense
        Mockito.when(this.expenseDAO.createExpense(Mockito.any(Expense.class))).thenAnswer(
                (Answer<Expense>) invocation -> {
                    Expense e = invocation.getArgument(0);
                    e.setExpenseId(1);
                    return e;
                }
        );
        //create expense ends

        Mockito.when(this.expenseDAO.getExpenseById(1)).thenReturn(
                testExpense
        );

        Set<Expense> expenses = new HashSet<>();
        expenses.add(testExpense);
        Mockito.when(this.expenseDAO.getAllExpenses()).thenReturn(expenses);

        Mockito.when(this.expenseDAO.updateExpense(Mockito.any(Expense.class))).thenAnswer(
                (Answer<Expense>) invocation -> {
                    Expense e = invocation.getArgument(0);
                    return e;
                }
        );

        Mockito.when(this.expenseDAO.deleteExpenseById(1)).thenReturn(true);

        expenseService = new ExpenseServiceImpl(expenseDAO);

    }

    @Order(1)
    @Test
    void create_expense(){
        Expense expense = new Expense(0, 100, "refund", 1);
        testExpense = expenseService.createExpense(expense);

        Assertions.assertNotNull(testExpense);
        Assertions.assertTrue(testExpense.getDateSubmitted() > 0);
        System.out.println(testExpense);
    }

    @Order(2)
    @Test
    void get_expense_by_id(){
        Expense e = expenseService.getExpenseById(1);
        Assertions.assertNotNull(e);
        Assertions.assertEquals(1, e.getExpenseId());
        System.out.println(e);
    }

    @Order(3)
    @Test
    void get_all_expenses(){
        Set<Expense> expenses = expenseService.getAllExpenses();

        Assertions.assertNotNull(expenses);
        Assertions.assertTrue(expenses.size() > 0);
        System.out.println(expenses);
    }

    @Order(4)
    @Test
    void approve_expense(){
        testExpense = this.expenseService.approveExpense(1, 1, "cuz im nice");

        Assertions.assertNotNull(testExpense);
        Assertions.assertTrue(testExpense.getDateReviewed() > 0);
        Assertions.assertEquals("approved", testExpense.getStatus());
        System.out.println(testExpense);
    }

    @Order(5)
    @Test
    void deny_expense(){
        testExpense = this.expenseService.denyExpense(1, 1, "cuz im mean");

        Assertions.assertNotNull(testExpense);
        Assertions.assertTrue(testExpense.getDateReviewed() > 0);
        Assertions.assertEquals("denied", testExpense.getStatus());
        System.out.println(testExpense);
    }

    @Order(6)
    @Test
    void delete_expense(){
        boolean result = this.expenseService.deleteExpense(1);
        Assertions.assertTrue(result);
    }

}
