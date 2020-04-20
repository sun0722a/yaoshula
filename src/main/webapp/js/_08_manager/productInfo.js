function doFirst() {
  addTimes = 0;
  fileSelect = document.getElementById("fileSelect");
  headPicture = document.getElementById("headPicture");
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
  formatItemsBox = document.getElementsByClassName("formatItemsBox");
  addProductFormatItemDiv = document.getElementsByClassName(
    "addProductFormatItemDiv"
  );
  addProductFormatItem = document.getElementsByClassName(
    "addProductFormatItem"
  );
  for (let n = 0; n < addProductFormatItem.length; n++) {
    addProductFormatItem[n].addEventListener("click", function () {
      addFormatItem(n);
    });
  }
  deleteProductFomat = document.getElementsByClassName("deleteProductFomat");
  productFomat = document.getElementsByClassName("productFomat");
  addProductFormat = document.getElementById("addProductFormat");
  addProductFormat.addEventListener("click", addProductFormatFunction);
  for (let n = 0; n < deleteProductFomat.length; n++) {
    deleteProductFomat[n].addEventListener("click", function () {
      deleteProductFormat(n);
    });
  }
  checkFormatBtn = document.getElementById("checkFormatBtn");
  changeFormatBtn = document.getElementById("changeFormatBtn");
  formatTitle1 = document.getElementsByName("formatTitle1")[0];
  formatTitle2 = document.getElementsByName("formatTitle2")[0];
  formatContent1 = document.getElementsByName("formatContent1");
  formatContent2 = document.getElementsByName("formatContent2");
  nullInputError = document.getElementById("nullInputError");

  checkFormatBtn.addEventListener("click", checkFormat);
  changeFormatBtn.addEventListener("click", changeFormat);
}
// 確認規格
function checkFormat() {
  emptyInput = false;

  if (productFomat[0].style.display == "") {
    if (formatTitle1.value != "") {
      for (let i = 0; i < formatContent1.length; i++) {
        if (formatContent1[i].value == "") {
          emptyInput = true;
        }
      }
    } else {
      emptyInput = true;
    }
  }
  if (productFomat[1].style.display == "") {
    if (formatTitle2.value != "") {
      for (let i = 0; i < formatContent2.length; i++) {
        if (formatContent2[i].value == "") {
          emptyInput = true;
        }
      }
    } else {
      emptyInput = true;
    }
  }

  if (emptyInput == false) {
    nullInputError.style.display = "none";
    formatTitle1.readOnly = true;
    for (let n = 0; n < formatContent1.length; n++) {
      formatContent1[n].readOnly = true;
    }
    formatTitle2.readOnly = true;
    for (let n = 0; n < formatContent2.length; n++) {
      formatContent2[n].readOnly = true;
    }
    for (let n = 0; n < addProductFormatItem.length; n++) {
      addProductFormatItem[n].style.display = "none";
    }
    deleteProductFormatItem = document.getElementsByClassName(
      "deleteProductFormatItem"
    );
    for (let n = 0; n < deleteProductFormatItem.length; n++) {
      deleteProductFormatItem[n].style.display = "none";
    }
    for (let n = 0; n < deleteProductFomat.length; n++) {
      deleteProductFomat[n].style.display = "none";
    }
    addProductFormat.style.display = "none";

    checkFormatBtn.style.display = "none";
    changeFormatBtn.style.display = "";

    顯示規格庫存
    













  } else {
    nullInputError.style.display = "";
  }
}
// 更改規格
function changeFormat() {
  formatTitle1.readOnly = false;
  for (let n = 0; n < formatContent1.length; n++) {
    formatContent1[n].readOnly = false;
  }
  formatTitle2.readOnly = false;
  for (let n = 0; n < formatContent2.length; n++) {
    formatContent2[n].readOnly = false;
  }

  for (let n = 0; n < addProductFormatItem.length; n++) {
    addProductFormatItem[n].style.display = "";
  }
  deleteProductFormatItem = document.getElementsByClassName(
    "deleteProductFormatItem"
  );
  for (let n = 0; n < deleteProductFormatItem.length; n++) {
    deleteProductFormatItem[n].style.display = "";
  }
  for (let n = 0; n < deleteProductFomat.length; n++) {
    deleteProductFomat[n].style.display = "";
  }
  if (addTimes != 2) {
    addProductFormat.style.display = "";
  }
  checkFormatBtn.style.display = "";
  changeFormatBtn.style.display = "none";
}
//刪除規格
function deleteProductFormat(n) {
  addTimes -= 1;
  if (addTimes != 2) {
    addProductFormat.style.display = "";
  }
  productFomat[n].style.display = "none";
  firstFormatItem = document.getElementsByClassName("firstFormatItem")[n];
  secondFormatItem = document.getElementsByClassName("secondFormatItem")[n];
  addProductFormatItemDiv = document.getElementsByClassName(
    "addProductFormatItemDiv"
  )[n];

  formatItemsBox[n].innerText = "";
  formatItemsBox[n].appendChild(firstFormatItem);
  formatItemsBox[n].appendChild(secondFormatItem);
  formatItemsBox[n].appendChild(addProductFormatItemDiv);

  if (n == 0) {
    console.log(formatTitle1);
    formatTitle1.value = "";
    for (let n = 0; n < formatContent1.length; n++) {
      formatContent1[n].value = "";
    }
  } else {
    formatTitle2.value = "";
    for (let n = 0; n < formatContent2.length; n++) {
      formatContent2[n].value = "";
    }
  }
}
//新增規格
function addProductFormatFunction() {
  addTimes += 1;
  if (addTimes == 2) {
    addProductFormat.style.display = "none";
    productFomat[0].style.display = "";
    productFomat[1].style.display = "";
  } else {
    productFomat[0].style.display = "";
  }
}
//新增規格細項
function addFormatItem(n) {
  formatTitle = document.createElement("div");
  formatTitle.setAttribute(
    "class",
    "col-5 px-0 d-flex justify-content-center align-items-center text-center"
  );

  formatItem = document.createElement("div");
  formatItem.setAttribute(
    "class",
    "col-5 px-0 d-flex justify-content-center align-items-center text-center"
  );
  formatItemInput = document.createElement("input");
  formatItemInput.type = "text";
  formatItemInput.name = "formatContent" + (n + 1);
  formatItemInput.setAttribute("class", "form-control");
  formatItem.appendChild(formatItemInput);

  removeMark = document.createElement("div");
  removeMark.setAttribute(
    "class",
    "col-2 d-flex justify-content-center align-items-center text-center"
  );
  removeIcon = document.createElement("i");
  removeIcon.setAttribute("class", "material-icons deleteProductFormatItem");
  removeIcon.style.fontSize = "36px";
  removeIcon.innerText = "remove_circle_outline";
  removeMark.appendChild(removeIcon);

  formatItemsRow = document.createElement("div");
  formatItemsRow.setAttribute("class", "form-group row mx-0");
  formatItemsRow.appendChild(formatTitle);
  formatItemsRow.appendChild(formatItem);
  formatItemsRow.appendChild(removeMark);

  addProductFormatItemDiv = document.getElementsByClassName(
    "addProductFormatItemDiv"
  );
  formatItemsBox[n].insertBefore(formatItemsRow, addProductFormatItemDiv[n]);

  //刪除規格細項
  removeIcon.onclick = function () {
    formatItemsBox[n].removeChild(this.parentNode.parentNode);
  };
}
window.addEventListener("load", doFirst);
