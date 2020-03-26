function doFirst() {
  orderRow = document.getElementsByClassName("orderRow");
  orderItems = document.getElementsByClassName("orderItems");

  for (let i = 0; i <= orderRow.length; i++) {
    if (orderRow[i]) {
      orderRow[i].addEventListener("click", function() {
        if (orderItems[i].style.display == "none") {
          orderItems[i].style.display = "";
        } else {
          orderItems[i].style.display = "none";
        }
      });
    }
  }
}

window.addEventListener("load", doFirst);
