function doFirst() {
  fileSelect = document.getElementById("fileSelect");
  headPicture = document.getElementById("headPicture");
  btSubmit = document.getElementById("btSubmit");
  password = document.getElementById("password");
  passwordCheck = document.getElementById("passwordCheck");
  registerForm = document.getElementById("registerForm");
  passwordError = document.getElementById("passwordError");
  btUserName = document.getElementById("btUserName");
  btEmail = document.getElementById("btEmail");
  userNameText = document.getElementById("userNameText");
  emailText = document.getElementById("emailText");
  userName = document.getElementById("userName");
  email = document.getElementById("email");

  // 在更動畫面裡，選擇照片後改變上面顯示的照片
  fileSelect.addEventListener("change", function() {
    readFile = new FileReader();
    readFile.readAsDataURL(fileSelect.files[0]);
    readFile.addEventListener("load", function() {
      source = this.result;
      headPicture.src = source;
      headPicture.style.maxWidth = "50%";
      headPicture.style.maxHeight = "200px";
    });
  });
  btUserName.addEventListener("click", check);
  btEmail.addEventListener("click", check);
  password.addEventListener("blur", checkPW);
  passwordCheck.addEventListener("blur", checkPassword);

  // btSubmit.addEventListener('click',checkPassword);
}
function checkPW() {
  if (password.value != passwordCheck.value && passwordCheck.value != "") {
    passwordCheck.value = "";
    passwordError.style.display = "contents";
  } else {
    passwordError.style.display = "none";
  }
}
function checkPassword() {
  // e.preventDefault();
  if (password.value != passwordCheck.value) {
    password.value = "";
    passwordCheck.value = "";
    passwordError.style.display = "contents";
  } else {
    passwordError.style.display = "none";
  }
}
function check(e) {
  e.preventDefault();
  xhr = new XMLHttpRequest();
  if (this.id == "btUserName") {
    xhr.open(
      "GET",
      "/yaoshula/register/checkUserName?userName=" + userName.value,
      false
    );
    xhr.send();
    if (xhr.responseText == "此帳號可使用") {
      userNameText.style.color = "green";
    } else {
      userNameText.style.color = "red";
    }
    userNameText.innerHTML = xhr.responseText;
  } else {
    xhr.open(
      "GET",
      "/yaoshula/register/checkEmail?email=" + email.value,
      false
    );
    xhr.send();
    if (xhr.responseText == "此信箱可使用") {
		emailText.style.color = "green";
    } else {
		emailText.style.color = "red";
    }
    emailText.innerHTML = xhr.responseText;
  }
}

window.addEventListener("load", doFirst);
