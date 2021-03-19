package dev.nasim.controller;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.google.gson.Gson;
import dev.nasim.daos.ExpenseDaoPostgres;
import dev.nasim.entities.Expense;
import dev.nasim.exception.BadFormatException;
import dev.nasim.exception.ExpenseNotFoundException;
import dev.nasim.exception.InsufficientPrivilegesException;
import dev.nasim.services.ExpenseService;
import dev.nasim.services.ExpenseServiceImpl;
import dev.nasim.utils.JwtUtil;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

import java.util.Set;

public class ExpenseController {
    private static Logger logger = Logger.getLogger(ExpenseController.class.getName());

    private ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDaoPostgres());

    public Handler createExpenseHandler = (Context ctx) -> {
        try {
            String token = ctx.header("Authorization");
            if (token == null){
                throw new InsufficientPrivilegesException();
            }
            DecodedJWT jwt = JwtUtil.isValidJWT(token);
            String role = jwt.getClaim("role").asString();
            if (!role.equals("employee")){
               throw new InsufficientPrivilegesException();
            }

            String body = ctx.body();
            Gson gson = new Gson();
            Expense expense = gson.fromJson(body, Expense.class);
            Expense resultExpense = expenseService.createExpense(expense);
            String resultExpenseJSON = gson.toJson(resultExpense);
            ctx.result(resultExpenseJSON);
            ctx.status(201);
            logger.info("Employee " + expense.getEmployeeId() + " has submitted a new Expense Request.");
        } catch (BadFormatException e){
            ctx.status(400);
            logger.error(e);
        } catch (InsufficientPrivilegesException e){
            ctx.status(403);
            logger.error(e);
        }
    };

    public Handler getAllExpensesHandler = (Context ctx) -> {
        Set<Expense> expenses = expenseService.getAllExpenses();
        Gson gson = new Gson();
        String expensesJSON = gson.toJson(expenses);
        ctx.result(expensesJSON);
        ctx.status(200);
    };

    public Handler getExpenseByIdHandler = (Context ctx) -> {
        try {
            int expenseId = Integer.parseInt(ctx.pathParam("expenseId"));
            Expense expense = expenseService.getExpenseById(expenseId);
            Gson gson = new Gson();
            String expenseJSON = gson.toJson(expense);
            ctx.result(expenseJSON);
            ctx.status(200);
        } catch (ExpenseNotFoundException e){
            ctx.status(404);
            logger.error(e);
        } catch (NumberFormatException e){
            ctx.status(400);
            logger.error(e);
        }
    };

    public Handler updateExpenseHandler = (Context ctx) -> {
        try {
            String token = ctx.header("Authorization");
            if (token == null){
                throw new InsufficientPrivilegesException();
            }
            DecodedJWT jwt = JwtUtil.isValidJWT(token);
            String role = jwt.getClaim("role").asString();
            if (!role.equals("manager")){
                throw new InsufficientPrivilegesException();
            }
            int expenseId = Integer.parseInt(ctx.pathParam("expenseId"));
            String body = ctx.body();
            Gson gson = new Gson();
            Expense expense = gson.fromJson(body, Expense.class);
            expense.setExpenseId(expenseId);

            String originalStatus = expenseService.getExpenseById(expenseId).getStatus();
            String newStatus = expense.getStatus();
            if (!originalStatus.equals(newStatus)) {
                if (newStatus.equals("approved")) {
                    expense = expenseService.approveExpense(expenseId, expense.getManagerId(), expense.getManagerReason());
                    logger.info("Expense " + expense.getExpenseId() + " has been approved.");
                }
                if (newStatus.equals("denied")) {
                    expense = expenseService.denyExpense(expenseId, expense.getManagerId(), expense.getManagerReason());
                    logger.info("Expense " + expense.getExpenseId() + " has been denied.");
                }
                ctx.result(gson.toJson(expense));
            }
            else {
                ctx.result(gson.toJson(expenseService.getExpenseById(expenseId)));
            }

        } catch (ExpenseNotFoundException e){
            ctx.status(404);
            logger.error(e);
        } catch (NumberFormatException e){
            ctx.status(400);
            logger.error(e);
        } catch (BadFormatException e){
            ctx.status(400);
            logger.error(e);
        } catch (InsufficientPrivilegesException e){
            ctx.status(403);
            logger.error(e);
        }
    };

    public Handler deleteExpenseHandler = (Context ctx) -> {
        try {
            String token = ctx.header("Authorization");
            if (token == null){
                throw new InsufficientPrivilegesException();
            }
            DecodedJWT jwt = JwtUtil.isValidJWT(token);
            String role = jwt.getClaim("role").asString();
            if (!role.equals("manager")){
                throw new InsufficientPrivilegesException();
            }

            int expenseId = Integer.parseInt(ctx.pathParam("expenseId"));
            if (expenseService.deleteExpense(expenseId)) {
                ctx.status(204);
                logger.info("Expense " + expenseId + " has been deleted.");
            }
            else {
                ctx.status(400);
                logger.error("Expense " + expenseId + " was unable to be deleted.");
            }
        } catch (ExpenseNotFoundException e){
            ctx.status(404);
            logger.error(e);
        } catch (InsufficientPrivilegesException e){
            ctx.status(403);
            logger.error(e);
        }
    };
}
