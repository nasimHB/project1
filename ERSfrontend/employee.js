//================parse

function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
};
//===================create
async function createReimbursement(){

    let loadedStorage = window.localStorage;
    const loadedToken = loadedStorage.getItem("jwt");
    let parsedToken = parseJwt(loadedToken);

    let body = {employeeId:parsedToken.id, expenseId:0};


	let amount = document.getElementById("expense-amount").value;
	let reason = document.getElementById("expense-reason").value;
    let date = document.getElementById("expense-date").value;
    
	
	if (amount > 0){
    body.amount = amount;
    body.employeeReason = reason;
    body.dateSubmitted = date;

    console.log(body);


    details = {method:'POST', headers:{Authorization:loadedToken}, body:JSON.stringify(body)};
    console.log(details);

    response = await fetch('http://localhost:7000/expenses', details);
    console.log(response)
    location.reload();

	} 
	else if(amount == 0){
		document.getElementById("invalid-amount").innerText = "Can't have 0 amount!";
	} else{
		document.getElementById("invalid-amount").innerText = "No negative amount!";
	}
};

//==============================date

function getFormattedDate(date) {
    let year = date.getFullYear();
    let month = (1 + date.getMonth()).toString().padStart(2, '0');
    let day = date.getDate().toString().padStart(2, '0');

    return month + '/' + day + '/' + year;
}


//==============logout
function logout(){
    localStorage.removeItem('jwt');
    callPage();
}

function callPage(){
    let loadedStorage = window.localStorage;
    const loadedToken = loadedStorage.getItem("jwt");
    console.log(loadedToken);
    if (loadedToken == null){
        window.location.href = './login.html';
    }

    if (loadedToken != null){
        let parsedToken = parseJwt(loadedToken);
        if (parsedToken.role == "manager"){
            console.log("manager");
            window.location.href = './manager.html';
        }
        
}
};
async function displayReimbursement(){
    
	let loadedStorage = window.localStorage;
        const loadedToken = loadedStorage.getItem("jwt");
        details = {method:'GET', headers:{Authorization:loadedToken}};

        response = await fetch('http://localhost:7000/expenses', details);
        let expenses = await response.json();
        expenses.sort((a, b) => (a.expenseId > b.expenseId) ? 1 : -1);

        let id = parseJwt(loadedToken).id;

        expenses = expenses.filter(expense => expense.employeeId == id);

        console.log(id);
        console.log(expenses);
        let tbody = document.getElementById("expense-table");

        //tbody.innerHTML = "";
        for (let expense of expenses){
		let newRow = document.createElement("tr");
		newRow.innerHTML = `<td>${expense.expenseId}</td>
        <td>${expense.amount}</td>
        <td>${expense.employeeReason}</td>
        <td>${expense.dateSubmitted}</td>
        <td>${expense.status}</td>`;
		tbody.appendChild(newRow);
	}
};

displayReimbursement();