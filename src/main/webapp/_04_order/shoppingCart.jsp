<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/_04_order/shoppingCart.css">
<script
	src="${pageContext.request.contextPath}/js/_04_order/shoppingCart.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.2/animate.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<script type="text/javascript">
var n;
var key;
	function deleteCart(n) {
		document.forms[0].action = "<c:url value='/order/updateShoppingCart?cmd=DEL&productFormatId="
				+ n + "' />";
		document.forms[0].method = "POST";
		document.forms[0].submit();
	}
	function modifyQuantity(key, index) {
		var x = "newQty" + index;
		var newQty = document.getElementById(x).value;
		document.forms[0].action = "<c:url value='/order/updateShoppingCart?cmd=QTY&productFormatId="
				+ key + "&newQty=" + newQty + "' />";
		document.forms[0].method = "POST";
		document.forms[0].submit();
	}
	function modifyFormat(key, index) {
		var x = "newFmt" + index;
		var newFmt = document.getElementById(x).value;
		document.forms[0].action = "<c:url value='/order/updateShoppingCart?cmd=FMT&productFormatId="
				+ key + "&newFmt=" + newFmt + "' />";
		document.forms[0].method = "POST";
		document.forms[0].submit();
	}
	function changeChoose(key, index) {
		var choose = document.getElementsByClassName("choose")[index].checked;
		document.forms[0].action = "<c:url value='/order/updateShoppingCart?cmd=CHS&productFormatId="
				+ key + "&choose=" + choose + "' />";
		document.forms[0].method = "POST";
		document.forms[0].submit();
	}
	function changeAll() {
		var chooseAll = document.getElementById("allCheck").checked;
		document.forms[0].action = "<c:url value='/order/updateShoppingCart?cmd=CSA&chooseAll="
				+ chooseAll + "' />";
		document.forms[0].method = "POST";
		document.forms[0].submit();
	}
</script>
</head>
<style>
a {
	color: black;
}

#a-style a {
	text-decoration: none;
}
</style>

<body class="bg-light">

	<div class="top_area">

		<div class="logo">
			<a href="<c:url value='#' />"> <img
				src="img/logo_transparent.png" style="width: 100px; height: 100px;"
				alt="">
			</a>
		</div>
		<div class="top-space"></div>
		<a class="nav-link dropdown-toggle text-dark"
			href="<c:url value='#' />" id="navbarDropdown" role="button"
			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			登入 </a>
		<div class="dropdown-menu" aria-labelledby="navbarDropdown">
			<a class="dropdown-item" href="#">Another action</a>
			<div class="dropdown-divider"></div>
			<a class="dropdown-item" href="<c:url value='#' />">Something
				else here</a>
		</div>
	</div>
	<ul id="a-style">
		<div class="test">
			<li class="dropdown "><i class="fas fa-bullhorn"></i> <a
				href="#home">論壇</a>
				<div class="dropdown-content">
					<a href="<c:url value='#' />">惡魔版</a> <a href="<c:url value='#' />">天使版</a>
				</div></li>
			<li class="dropdown "><i class="fas fa-shopping-bag"></i> <a
				href="<c:url value='#' />">商城</a>
				<div class="dropdown-content">
					<a href="<c:url value='#' />">購物區</a> <a href="<c:url value='#' />">購物車</a>
					<a href="<c:url value='#' />">歷史訂單</a>
				</div></li>
			<li class="dropdown "><i class="fas fa-user-friends"></i> <a
				href="<c:url value='#' />">關於我們</a>
				<div class="dropdown-content">
					<a href="<c:url value='#' />">創建理念</a> <a
						href="<c:url value='#' />">團隊介紹</a> <a href="<c:url value='#' />">關於我們</a>
				</div></li>
		</div>
	</ul>

	<div class="cart-box p-2">
		<h1>購物車</h1>
		<!-- 標題======================================== -->
		<div class="items-menu border border-dark">
			<!-- <hr style="background: black;" /> -->
			<div class="row p-2">
				<div
					class="col-1 h4 m-0 d-flex justify-content-center align-items-center ">
					<input type="checkbox" id="allCheck" onchange="changeAll()"/>
				</div>
				<div
					class="col-2 h6 m-0 d-flex justify-content-start align-items-center">
					全選</div>
				<div
					class="col-2 h5 m-0 d-flex justify-content-center align-items-center">
					商品名稱</div>
				<div
					class="col-3 h5 m-0 d-flex justify-content-center align-items-center">
					規格</div>
				<div
					class="col-1 h5 m-0 d-flex justify-content-center align-items-center">
					單價</div>
				<div
					class="col-1 h5 m-0 d-flex justify-content-center align-items-center">
					數量</div>
				<div
					class="col-1 h5 m-0 d-flex justify-content-center align-items-center">
					總價</div>
				<div class="col-1 d-flex justify-content-center align-items-center"></div>
			</div>

			<hr class="m-0" style="background: black;" />
			<form action="">
				<!-- 內容物=================================== -->
				<c:forEach var="cartMap" varStatus="vs"
					items="${ShoppingCart.content}">
					<c:forEach var="orderMap" items="${cartMap.value}">

						<div class="row p-2 cartItem">
							<div
								class="col-1 d-flex justify-content-center align-items-center">
								<input type="checkbox" class="choose" <c:if test="${fn:startsWith(cartMap.key,'y')}"> checked </c:if> onchange="changeChoose(${cartMap.key},${vs.index})"/>
							</div>
							<div
								class="col-2 d-flex justify-content-center align-items-center">
								<img
									src="${pageContext.request.contextPath}/init/getProductImage?id=${orderMap.key.productId}"
									style="max-width: 80%; max-height: 150px;" />
							</div>
							<div
								class="col-2 h4 m-0 d-flex justify-content-center align-items-center">
								${orderMap.key.productName}</div>
							<div
								class="col-3 h5 m-0 d-flex justify-content-center align-items-center">
								<c:choose>
									<c:when
										test="${(orderMap.key.formatContent1=='')&&(orderMap.key.formatContent2=='')}"> 無 </c:when>
									<c:otherwise>
										<select name="format" value="" id="newFmt${vs.index}"
											style="max-width: 100%;"
											onchange="modifyFormat(${cartMap.key},${vs.index})">
											<c:choose>
												<c:when test="${(orderMap.key.formatContent2=='')}">
														<c:forEach var="productSet" items="${orderMap.value}">
															<option value="${productSet.formatContent1}"
																<c:if test="${(orderMap.key.formatContent1==productSet.formatContent1)}"> selected </c:if>>${productSet.formatContent1}</option>
														</c:forEach>
												</c:when>
												<c:otherwise>
													<c:forEach var="productSet" items="${orderMap.value}">
														<option
															value="${productSet.formatContent1},${productSet.formatContent2}"
															<c:if test="${(orderMap.key.formatContent1==productSet.formatContent1)&&(orderMap.key.formatContent2==productSet.formatContent2)}"> selected </c:if>>${productSet.formatContent1},${productSet.formatContent2}</option>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</select>
									</c:otherwise>
								</c:choose>
							</div>
							<div
								class="col-1 h5 m-0 d-flex justify-content-center align-items-center">
								$ <span class="singlePrice">${orderMap.key.unitPrice}</span>
							</div>
							<div
								class="col-1 h5 m-0 d-flex justify-content-center align-items-center"
								style="padding: 0px;">
								<select name="count" id="newQty${vs.index}"
									style="max-width: 100%;"
									onchange="modifyQuantity(${cartMap.key},${vs.index})">
									<c:forEach begin="1" end="10" var="number">
										<option value="${number}"
											<c:if test="${orderMap.key.quantity==number}"> selected </c:if>>${number}</option>
									</c:forEach>
								</select>
							</div>
							<div
								class="col-1 h5 m-0 d-flex justify-content-center align-items-center ">
								$ <span class="singleTotal">${orderMap.key.unitPrice*orderMap.key.quantity}</span>
							</div>
							<div
								class="col-1 d-flex justify-content-center align-items-center">
								<button class="cancel p-0" onclick="deleteCart(${cartMap.key})">
									<img
										src="${pageContext.request.contextPath}/image/_04_order/trash.png"
										style="max-width: 100%;" />
								</button>
							</div>
						</div>
					</c:forEach>
				</c:forEach>


				<hr class="m-0" style="background: black;" />
				<!-- 總金額================================================= -->
				<div class="row p-2">
					<div class="col-5"></div>
					<div
						class="col-4 h4 m-0 d-flex justify-content-center align-items-center">
						總金額： $ <span id="totalPrice"></span>
					</div>
					<div class="col-3 d-flex justify-content-center align-items-center">
						<input type="button" value="確認訂單" style="max-width: 100%;" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>