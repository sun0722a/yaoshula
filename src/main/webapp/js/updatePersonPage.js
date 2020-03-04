function doFirst() {
  edit = document.getElementById("edit");
  fileSelect = document.getElementById("fileSelect");
  btSubmit = document.getElementById("btSubmit");
  personalUpdates = document.getElementsByClassName("personalUpdate");
  headPicture = document.getElementById("headPicture");
  personPage = document.getElementById("personPage");

  edit.addEventListener("click", update);
  edit.addEventListener("mouseover", function() {
    edit.style.border = "2px solid rgb(2, 117, 69)";
    edit.style.background = "rgb(26, 202, 129)";
  });
  edit.addEventListener("mouseout", function() {
    edit.style.border = "2px solid #fff";
    edit.style.background = "rgb(118, 206, 169)";
  });

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
  btSubmit.addEventListener("click", submit);
}


function submit() {
  edit.style.visibility = "visible";
  fileSelect.style.visibility = "hidden";
  btSubmit.style.visibility = "hidden";
  email = document.getElementsByName("email")[0];
  phone = document.getElementsByName("phone")[0];
  address = document.getElementsByName("address")[0];
  let newEmail = `${email.value}`;
  let newPhone = `${phone.value}`;
  let newAddress = `${address.value}`;
  // for (let i = 0; i < personalUpdates.length; i++) {
  //   personalUpdates[i].firstChild.remove();
  // }
  personalUpdates[0].innerHTML = newEmail;
  personalUpdates[1].innerText = newPhone;
  personalUpdates[2].innerText = newAddress;
}


function update() {

  // // personPage = document.getElementById("personPage");
  // form = document.createElement("form");
  // form.id='person';
  // form.method='post';
  // console.log(form);

  oldemail = personalUpdates[0].firstChild.nodeValue;
  oldphone = personalUpdates[1].firstChild.nodeValue;
  oldaddress = personalUpdates[2].firstChild.nodeValue;
  edit.style.visibility = "hidden";
  fileSelect.style.visibility = "visible";
  btSubmit.style.visibility = "visible";
  for (let i = 0; i < personalUpdates.length; i++) {
    personalUpdates[i].innerText = "";
  }
  let updateEmail = document.createElement("input");
  let updatePhone = document.createElement("input");
  let updateAddress = document.createElement("input");
  updateToInput(0, updateEmail, oldemail, "email");
  updateToInput(1, updatePhone, oldphone, "phone");
  updateToInput(2, updateAddress, oldaddress, "address");

//   form.appendChild(personPage);
//   console.log(form);
}
function updateToInput(index, inputTitle, inputValue, inputName) {
  inputTitle.type = "text";
  inputTitle.name = inputName;
  inputTitle.value = inputValue;
  inputTitle.style.font = "18px Tahoma";
  inputTitle.style.width = "70%";
  inputTitle.style.padding = "5px";
  personalUpdates[index].appendChild(inputTitle);
}

window.addEventListener("load", doFirst);
