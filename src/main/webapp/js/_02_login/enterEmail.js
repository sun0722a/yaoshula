function doFirst(){
	submitbtn = document.getElementById('submitbtn');
	emailInput = document.getElementById('emailInput');
	emailError = document.getElementById('emailError');
	
	emailInput.addEventListener('blur',checkEmail);
	emailInput.addEventListener('focus',disabledBtn)
}

function checkEmail(){
	if(!emailInput.value.includes('@')){
		emailError.style.display = 'block';
		emailError.style.color = 'red';
		submitbtn.disabled = true;
	}else{
		emailError.style.display = 'none';
		submitbtn.disabled = false;
	}
}

function disabledBtn(){
	submitbtn.disabled = true;
}

window.addEventListener('load',doFirst);