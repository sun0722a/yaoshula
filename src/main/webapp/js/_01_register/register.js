function doFirst() {
  fileSelect = document.getElementById("fileSelect");
  headPicture = document.getElementById("headPicture");
  btSubmit = document.getElementById("btSubmit");
  password = document.getElementById("password");
  passwordCheck = document.getElementById("passwordCheck");
  registerForm = document.getElementById("registerForm");
  passwordError = document.getElementById("passwordError");

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
  password.addEventListener("blur", checkPW);
  passwordCheck.addEventListener("blur", checkPassword);

  // btSubmit.addEventListener('click',checkPassword);
}
function checkPW() {
    if (password.value != passwordCheck.value&&passwordCheck.value!="") {
      passwordCheck.value = "";
      passwordError.style.display = "contents";
    } else{
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
function checkUserName() {
  e.preventDefault();
  window.open("/register/checkUserName");
}
window.addEventListener("load", doFirst);
