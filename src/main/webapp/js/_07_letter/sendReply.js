function doFirst() {
	
	buttonSend = document.getElementById("buttonSend");
	buttonReply = document.getElementById("buttonReply");
	donotSend = document.getElementById("donotSend");
	donotReply = document.getElementById("donotReply");
	
	buttonSend.addEventListener("click",checkStatus);
	buttonReply.addEventListener("click",checkStatus);
	
}

function checkStatus(){
	alert(buttonReply.value);
	xhr = new XMLHttpRequest();
	
	if(this.id == "buttonSend"){
		xhr.open(
		 "GET",
		 "/yaoshula/letter/checkUseLetter",
		 true
		);
		xhr.send();
	}else{
		xhr.open(
		"GET",
		"/yaoshula/letter/checkUseLetter?replyType=" + buttonReply.value,
		true
		);
		xhr.send();
		if(xhr.responseText == "可回!"){
			donotReply.style.display = "block";
		}else{
			donotReply.style.display = "none";
		}
	}
}

window.addEventListener("load", doFirst);