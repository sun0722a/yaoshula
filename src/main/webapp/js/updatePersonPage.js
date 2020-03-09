function doFirst() {
  edit = document.getElementById("edit");
  fileSelect = document.getElementById("fileSelect");
  personalUpdates = document.getElementsByClassName("personalUpdate");
  headPicture = document.getElementById("headPicture");
  btSubmit = document.getElementById("btSubmit");

  edit.addEventListener("click", update);

  edit.addEventListener("mouseover", function() {
    edit.style.border = "2px solid rgb(2, 117, 69)";
    edit.style.background = "rgb(26, 202, 129)";
  });
  edit.addEventListener("mouseout", function() {
    edit.style.border = "2px solid #fff";
    edit.style.background = "rgb(118, 206, 169)";
  });

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
}

function update() {
  edit.style.visibility = "hidden";
  fileSelect.style.visibility = "visible";
  btSubmit.style.visibility = "visible";

  // 抓原本表格上的值
  oldemail = personalUpdates[0].innerText;
  oldphone = personalUpdates[1].innerText;
  oldaddress = personalUpdates[2].innerText;

  // 清空td裡的字
  for (let i = 0; i < personalUpdates.length; i++) {
    personalUpdates[i].innerText = "";
  }

  // 動態新增input
  updateEmail = document.createElement("input");
  let updatePhone = document.createElement("input");
  let updateAddress = document.createElement("input");
  updateToInput(0, updateEmail, "email", oldemail, "email");
  updateToInput(1, updatePhone, "text", oldphone, "phone");
  updateToInput(2, updateAddress, "text", oldaddress, "address");

  // 檢查email欄有沒有輸入東西
  updateEmail.addEventListener("keyup", emailError);
}
function emailError() {
  errorEmail = document.getElementsByClassName("errorMsg")[0];
  if (updateEmail.value == "") {
    errorEmail.style.display = "contents";
    btSubmit.disabled = "true";
  } else {
    errorEmail.style.display = "none";
    btSubmit.disabled = "";
  }
}
// 設定input屬性
function updateToInput(index, inputTitle, inputType, inputValue, inputName) {
  inputTitle.type = inputType;
  inputTitle.value = inputValue;
  inputTitle.name = inputName;
  inputTitle.style.font = "18px Tahoma";
  inputTitle.style.width = "70%";
  inputTitle.style.padding = "5px";
  personalUpdates[index].appendChild(inputTitle);
}

window.addEventListener("load", doFirst);
