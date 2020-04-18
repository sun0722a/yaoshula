function doFirst(){
	passwordError = document.getElementById("passwordError");
	enterPassword = document.getElementById("enterPassword");
	checkPassword = document.getElementById("checkPassword");
	
	enterPassword.addEventListener("blur",check)
	checkPassword.addEventListener("blur",checkpswd);
}



function check(){
	if (enterPassword.value != checkPassword.value && checkPassword.value != "") {
		checkPassword.value = "";
	    passwordError.style.display = "block";
	    passwordError.style.color = "red";
	    
	  } else {
	    passwordError.style.display = "none";
	  }
}

function checkpswd(){
	if(enterPassword.value != checkPassword.value){
		enterPassword.value = "";
		checkPassword.value = "";
		passwordError.style.display = "block";
		passwordError.style.color = "red";
	}else {
	    passwordError.style.display = "none";
	  }
}

window.addEventListener("load",doFirst);