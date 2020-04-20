function doFirst(){
	passwordError = document.getElementById("passwordError");
	enterPassword = document.getElementById("enterPassword");
	checkPassword = document.getElementById("checkPassword");
	checkbtn = document.getElementById("checkbtn");
	
	enterPassword.addEventListener("blur",check)
	checkPassword.addEventListener("blur",checkpswd);
	
	enterPassword.addEventListener("focus",forbidden);
	checkPassword.addEventListener("focus",forbidden);
	
}

function check(){
	if (enterPassword.value != checkPassword.value && checkPassword.value != "") {
		checkPassword.value = "";
	    passwordError.style.display = "block";
	    passwordError.style.color = "red";
	    
	  } else {
	    passwordError.style.display = "none";
	    checkbtn.disabled = false;
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
	    checkbtn.disabled = false;
	  }
}

function forbidden(){
	checkbtn.disabled = true;
}

window.addEventListener("load",doFirst);