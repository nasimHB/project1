
//parse JWT-------------
function parseJwt(token) {
	var base64Url = token.split('.')[1];
	var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
	var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
		return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
	}).join(''));

	return JSON.parse(jsonPayload);
};


//login--------
async function login(){

	    let username = document.getElementById("username").value;
        let pasword = document.getElementById("password").value;
		console.log("login");
		//let login = {"username":username, "password":password};
        //const details = {method:'POST', body:JSON.stringify(login)};
		const credentials = {
            username:username,
            pswrd:password.value
        }
		console.log(credentials);
        const details = {
            method: 'POST',
            body: JSON.stringify(credentials)
        }
	
	const httpResponse = await fetch("http://localhost:7000/login",details) // return a cookie and save 
	
	if(httpResponse.status!=200){
		document.getElementById("login-error").innerHTML = "error";
			

	}else{
    console.log(httpResponse);
	const jwt = await httpResponse.json();// get jwt
	console.log(jwt);
	let parsejwt=parseJwt(jwt);
	localStorage.setItem('jwt',jwt);
	console.log(localStorage.getItem('jwt'));
	callPage();
    }
	}

	//select page----------------
function callPage(){
	let loadedStorage = window.localStorage;
        const loadedToken = loadedStorage.getItem('jwt');
        console.log(loadedToken);
        if (loadedToken != null){
			//const decoded = JSON.parse(atob(loadedToke.split('.')[1]));
            let parsedToken = parseJwt(loadedToken);
            if (parsedToken.role == "manager"){
                console.log("manager");
                window.location.href = './manager.html';
            }
            if (parsedToken.role == "employee"){
                console.log("employee");
                window.location.href = './employee.html';
            }
        }
    }
		

        
    

    
	