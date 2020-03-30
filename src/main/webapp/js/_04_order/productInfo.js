//按下加入購物車的小小動畫
$(document).ready(function() {
	$("#joinCart").click(function() {
		$(".animateCart").show();
	});
});

function doFirst() {
	buyNow = document.getElementById("buyNow");
	joinCart = document.getElementById("joinCart");
	buyForm = document.getElementById("buyForm");

	buyNow.addEventListener("click", function() {
		// buyForm.action="<c:url value='/order/checkOrder' />";
//		buyForm.action="{:U('/order/checkOrder')}";
//		buyForm.submit();
	});
	joinCart.addEventListener("click", function() {
		// buyForm.action="<c:url value='/order/shoppingCart' />";
//		buyForm.action="{:U('/order/shoppingCart')}";
//		buyForm.submit();
	});
}

window.addEventListener("load", doFirst);
