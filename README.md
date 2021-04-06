## ERS(Expense Reimbursement system)
This system has two main types of users.
- Employees 
- managers
- All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. 
-  The application follows RESTful conventions for its resources.

## Technologies
- javalin
- DAOpostgres
- postgresql
- java-jwt
- log4j 
- junit5 
- mockito

## user stories
- Employees should be able to login.
- Employees can submit expenses.
- Every every expense should have an amount and a reason, status, date submitted, and date approved/denied.
- valid expense statuses are "pending", "approved", "denied"
- Employees can see the status of any of their own reimbursements past and present.
-
- Managers should be able to login
- Managers can view all employees reimbursements
- Managers can approve or deny reimbursements.
- When approving or denying managers can optionally add a reason why
