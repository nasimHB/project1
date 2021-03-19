



//--------------parse JWT
function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
};

//function logout -------------=========-------=======
function logout(){
    localStorage.removeItem('jwt');
    callPage();
}

//---------------callpage
function callPage(){
    let loadedStorage = window.localStorage;
    const loadedToken = loadedStorage.getItem("jwt");
    //console.log(loadedToken);
    if (loadedToken == null){
        window.location.href = './login.html';
    }
    if (loadedToken != null){
        let parsedToken = parseJwt(loadedToken);
        if (parsedToken.role == "employee"){
           // console.log("employee");
            window.location.href = './employee.html';
        }
    }
};


// display expense-----------------------------------------

    async function displayReimbursement(){
        let loadedStorage = window.localStorage;
        const loadedToken = loadedStorage.getItem("jwt");
        details = {method:'GET', headers:{Authorization:loadedToken}};

        response = await fetch('http://localhost:7000/expenses', details);
        let expenses = await response.json();
        expenses.sort((a, b) => (a.expenseId > b.expenseId) ? 1 : -1)

        //console.log(expenses);

        let table = document.getElementById("reimb-edit-table");
    
        let counter = 0;
        for (let expense of expenses){
    
            newRow = document.createElement("tr");
            newRow.innerHTML = 
            `<td id="reimb-field-reimbId-${counter}">${expense.expenseId}</td>
            <td id="reimb-field-empId-${counter}">${expense.employeeId }</td>
            <td id="reimb-field-amount-${counter}">${expense.amount}</td>
            <td id="reimb-field-reason-${counter}">${expense.employeeReason}</td>
            <td id="reimb-field-date-${counter}">${expense.dateSubmitted}</td>
            <td id="reimb-field-status-${counter}">${expense.status}</td>`;
            
            table.appendChild(newRow);
    
            //let reimbSelect = document.getElementById("reimb-select-status");
           // let cloneSelect = reimbSelect.cloneNode(true);
            //cloneSelect.id = `reimb-select-status-${counter}`;
            

            let reimbBtn = document.getElementById("approve-btn");
            let cloneBtn = reimbBtn.cloneNode(true);
            cloneBtn.id = `approve-btn-${counter}`;
            cloneBtn.setAttribute("onclick","approve(" + counter +")");
           // cloneBtn.addEventListener("click", ()=>{approve(counter)});
            newRow.appendChild(cloneBtn);

             reimbBtn = document.getElementById("deny-btn");
             cloneBtn = reimbBtn.cloneNode(true);
            cloneBtn.id = `deny-btn-${counter}`;
            cloneBtn.setAttribute("onclick","deny(" + counter +")");
           // cloneBtn.addEventListener("click", ()=>{deny(counter)});
            newRow.appendChild(cloneBtn);
    
            counter++;
        }
    
        counter = 0;
    
    };

    document.getElementById("logout-btn").addEventListener("click",logout);

    async function approve(id){
        console.log(`reimb-field-reimbId-${id}`);
        let expense_id = document.getElementById(`reimb-field-reimbId-${id}`).innerHTML;
    
console.log(expense_id);

        let loadedStorage = window.localStorage;
        const loadedToken = loadedStorage.getItem("jwt");
        //console.log(loadedToken);
        let parsedToken = parseJwt(loadedToken);

        console.log(parsedToken);

        let body = {}
        body.status = "approved";
        body.expenseId = expense_id;
        body.managerId = parsedToken.id;
        body = JSON.stringify(body);
        
        console.log(body)
        details = {method:'PUT', headers:{Authorization:loadedToken}, body:body};

        url = `http://localhost:7000/expenses/${expense_id}`;
       // console.log(url);
        response = await fetch(url, details);
        // loadTable();
        body = response.json()
        //console.log(body);
        location.reload();
 };

    async function deny(id){

        console.log(`reimb-field-reimbId-${id}`);
        let expense_id = document.getElementById(`reimb-field-reimbId-${id}`).innerHTML;
    
console.log(expense_id);

        let loadedStorage = window.localStorage;
        const loadedToken = loadedStorage.getItem("jwt");
        let parsedToken = parseJwt(loadedToken);

        console.log(parsedToken);

        let body = {}
        body.status = "denied";
        body.expenseId = expense_id;
        body.managerId = parsedToken.id;
        body = JSON.stringify(body);
        
        console.log(body)
        details = {method:'PUT', headers:{Authorization:loadedToken}, body:body};

        url = `http://localhost:7000/expenses/${expense_id}`;
        //console.log(url);
        response = await fetch(url, details);
        // loadTable();
        body = response.json()
       // console.log(body);
        location.reload();
    };
    


	//  edit reimbursement
	displayReimbursement();





