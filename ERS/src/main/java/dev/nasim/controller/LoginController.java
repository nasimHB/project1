package dev.nasim.controller;

import com.google.gson.Gson;
import dev.nasim.daos.EmployeeDaoPostgres;
import dev.nasim.daos.ManagerDaoPostgres;
import dev.nasim.utils.JwtUtil;
import dev.nasim.entities.Employee;
import dev.nasim.entities.Manager;
import dev.nasim.services.EmployeeService;
import dev.nasim.services.EmployeeServiceImpl;
import dev.nasim.services.ManagerService;
import dev.nasim.services.ManagerServiceImpl;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class.getName());


    private EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoPostgres());
    private ManagerService managerService = new ManagerServiceImpl(new ManagerDaoPostgres());


    public Handler loginHandler = (Context ctx) -> {
        String body = ctx.body();
        Gson gson = new Gson();
        Manager manager = gson.fromJson(body, Manager.class);
        if (manager == null){
            ctx.status(400);
            return;
        }
        manager = managerService.getManagerByUsernameAndPswrd(manager.getUsername(), manager.getPswrd());
        Employee employee = gson.fromJson(body, Employee.class);
        if (employee == null){
            ctx.status(400);
            return;
        }
        employee = employeeService.getEmployeeByUsernameAndPswrd(employee.getUsername(), employee.getPswrd());

        if (manager != null){
            String token = JwtUtil.generate(manager.getManagerId(),"manager", manager.getUsername());
            ctx.cookie("Authorization", token, 86400);
            ctx.result(gson.toJson(token));
            logger.info("Manager " + manager.getUsername() + " successfully logged in.");
            ctx.status(200);
        }
        else if (employee != null){
            String token = JwtUtil.generate(employee.getEmployeeId(),"employee", employee.getUsername());
            ctx.cookie("Authorization", token, 86400);
            ctx.result(gson.toJson(token));
            logger.info("Employee " + employee.getUsername() + " successfully logged in.");
            ctx.status(200);
        }
        else if(manager != null) {
            logger.error("Unsuccessful login attempt for " + manager.getUsername());
            ctx.status(404);
        }
        else{
            logger.error("Unsuccessful login attempt" );
            ctx.status(404);
        }
    };

}

