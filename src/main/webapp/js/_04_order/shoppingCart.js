function doFirst() {
  count = document.getElementsByClassName("count");
  //   singlePrice = document.getElementsByClassName("singlePrice");
  allCheck = document.getElementById("allCheck");
  choose = document.getElementsByClassName("choose");
  //   cancel = document.getElementsByClassName("cancel");

  allCheck.addEventListener("change", function() {
    if (this.checked) {
      for (let i = 0; i < choose.length; i++) {
        choose[i].checked = true;
      }
    } else {
      for (let i = 0; i < choose.length; i++) {
        choose[i].checked = false;
      }
    }
    checkMoney();
  });

  for (let i = 0; i < choose.length; i++) {
    choose[i].addEventListener("change", checkMoney);
  }

  checkMoney();
}

function checkMoney() {
  singleTotal = document.getElementsByClassName("singleTotal");
  totalPrice = document.getElementById("totalPrice");

  //   計算總金額(有勾選的)
  let totalMoney = 0;
  for (let i = 0; i < choose.length; i++) {
    if (choose[i].checked) {
      // for (let j = 0; j < singleTotal.length; j++) {
      totalMoney += parseInt(singleTotal[i].innerText);
      // }
    }
  }
  totalPrice.innerText = totalMoney;
  // //   計算單項總價
  // for(let n=0;n<singlePrice.length;n++){
  //     let countNumber = parseInt(count[n].value);
  //     let price = parseInt(singlePrice[n].innerText);
  //     singleTotal[n].innerText = price * countNumber;
  // }
}

window.addEventListener("load", doFirst);
