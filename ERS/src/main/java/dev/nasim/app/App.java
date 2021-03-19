package dev.nasim.app;


    import dev.nasim.controller.ExpenseController;
    import dev.nasim.controller.LoginController;
    import io.javalin.Javalin;

    public class App {

        public static void main(String[] args) {
            Javalin app = Javalin.create(
                    config -> {
                        config.enableCorsForAllOrigins();
                    });

            LoginController lc = new LoginController();
            ExpenseController ec = new ExpenseController();

            // Handle expenses
            app.post("/expenses", ec.createExpenseHandler);

            app.get("/expenses", ec.getAllExpensesHandler);
            app.get("/expenses/:expenseId", ec.getExpenseByIdHandler);

            app.put("/expenses/:expenseId", ec.updateExpenseHandler);

            app.delete("/expenses/:expenseId", ec.deleteExpenseHandler);


            app.post("/login", lc.loginHandler);

            app.start();
        }
    }

