function doFirst() {
  edit = document.getElementById("edit");
  fileSelect = document.getElementById("fileSelect");
  personalUpdates = document.getElementsByClassName("personalUpdate");
  headPicture = document.getElementById("headPicture");
  btSubmit = document.getElementById("btSubmit");
  btCancel = document.getElementById("btCancel");
  city = document.getElementById("city");
  area = document.getElementById("area");
  address = document.getElementById("address");

  edit.addEventListener("click", update);

  edit.addEventListener("mouseover", function () {
    edit.style.border = "2px solid rgb(2, 117, 69)";
    edit.style.background = "rgb(26, 202, 129)";
  });
  edit.addEventListener("mouseout", function () {
    edit.style.border = "2px solid #fff";
    edit.style.background = "rgb(118, 206, 169)";
  });

  // 在更動畫面裡，選擇照片後改變上面顯示的照片
  fileSelect.addEventListener("change", function () {
    readFile = new FileReader();
    readFile.readAsDataURL(fileSelect.files[0]);
    readFile.addEventListener("load", function () {
      source = this.result;
      headPicture.src = source;
      headPicture.style.maxWidth = "50%";
      headPicture.style.maxHeight = "200px";
    });
  });
}

function update() {
  // 下拉式地址
  new TwCitySelector();
  // 下拉式地址

  edit.style.visibility = "hidden";
  fileSelect.style.visibility = "visible";
  btSubmit.style.visibility = "visible";
  btCancel.style.visibility = "visible";

  // 抓原本表格上的值
  oldphone = personalUpdates[0].innerText;

  // 清空td裡的字
  for (let i = 0; i < personalUpdates.length; i++) {
    personalUpdates[i].innerText = "";
  }

  // 動態新增下拉式地址
  let addressSelect = document.createElement("div");
  addressSelect.setAttribute("role", "tw-city-selector");
  addressSelect.setAttribute("data-county-value", city.innerText);
  addressSelect.setAttribute("data-district-value", area.innerText);
  addressSelect.id = "address";
  personalUpdates[1].appendChild(addressSelect);

  // 動態新增input
  let updatePhone = document.createElement("input");
  let updateAddress = document.createElement("input");
  updateToInput(0, updatePhone, "text", oldphone, "phone");
  updateToInput(1, updateAddress, "text", address.innerText, "address");
  updateEmail.required = true;
  updatePhone.placeholder = "0912345678";
  updatePhone.maxLength = "10";
  updatePhone.addEventListener("keyup", function () {
    this.value = this.value.replace(/[^\d]/g, "");
  });
}
// 設定input屬性
function updateToInput(index, inputTitle, inputType, inputValue, inputName) {
  inputTitle.type = inputType;
  inputTitle.value = inputValue;
  inputTitle.name = inputName;
  inputTitle.className = "updateInput";
  personalUpdates[index].appendChild(inputTitle);
}

window.addEventListener("load", doFirst);
