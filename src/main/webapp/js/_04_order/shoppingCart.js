function doFirst() {
  count = document.getElementsByClassName("count");
  //   singlePrice = document.getElementsByClassName("singlePrice");
  allCheck = document.getElementById("allCheck");
  choose = document.getElementsByClassName("choose");
  //   cancel = document.getElementsByClassName("cancel");
  cartForm = document.getElementById("cartForm");

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
  });

  for (let i = 0; i < count.length; i++) {
    count[i].addEventListener("change", function() {
      // cartForm.submit();
    });
  }

  //   for (let i = 0; i < cancel.length; i++) {
  //     cancel[i].addEventListener("click", function() {
  //       document.getElementsByClassName("cartItem")[i].re;

  //     });
  //   }
}

// function checkMoney() {
//   singleTotal = document.getElementsByClassName("singleTotal");
//   totalPrice = document.getElementById("totalPrice");

//   //   計算單項總價
//   for(let n=0;n<singlePrice.length;n++){
//       let countNumber = parseInt(count[n].value);
//       let price = parseInt(singlePrice[n].innerText);
//       singleTotal[n].innerText = price * countNumber;
//   }

//   //   計算總金額
//   let totalMoney = 0;
//   for (let j = 0; j < singleTotal.length; j++) {
//     totalMoney += parseInt(singleTotal[j].innerText);
//   }
//   totalPrice.innerText = totalMoney;
// }

window.addEventListener("load", doFirst);
