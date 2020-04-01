<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/_04_order/check.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/_04_order/cartCheck.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.2/animate.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
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
			<a href=""> <img src="img/logo_transparent.png"
				style="width: 100px; height: 100px;" alt="">
			</a>
		</div>
		<div class="top-space"></div>
		<a class="nav-link dropdown-toggle text-dark" href="#"
			id="navbarDropdown" role="button" data-toggle="dropdown"
			aria-haspopup="true" aria-expanded="false"> 登入 </a>
		<div class="dropdown-menu" aria-labelledby="navbarDropdown">
			<a class="dropdown-item" href="#">Another action</a>
			<div class="dropdown-divider"></div>
			<a class="dropdown-item" href="#">Something else here</a>
		</div>
	</div>
	<ul id="a-style">
		<div class="test">
			<li class="dropdown "><i class="fas fa-bullhorn"></i> <a
				href="#home">論壇</a>
				<div class="dropdown-content">
					<a href="#">惡魔版</a> <a href="#">天使版</a>
				</div></li>
			<li class="dropdown "><i class="fas fa-shopping-bag"></i> <a
				href="#news">商城</a>
				<div class="dropdown-content">
					<a href="#">購物區</a> <a href="#">購物車</a> <a href="#">歷史訂單</a>
				</div></li>
			<li class="dropdown "><i class="fas fa-user-friends"></i> <a
				href="">關於我們</a>
				<div class="dropdown-content">
					<a href="#">創建理念</a> <a href="#">團隊介紹</a> <a href="#">關於我們</a>
				</div></li>
		</div>
	</ul>

	<div class="cart-box">
		<div>確認訂單</div>
		<div class="items-menu">
			<hr style="background: black;">
			<div class="cart-text">
				<div class="text-items">商品圖片</div>
				<div class="text-items">商品名稱</div>
				<div class="text-items">規格</div>
				<div class="text-items">單價</div>
				<div class="text-items">數量</div>
				<div class="text-items">總價</div>
			</div>
			<hr style="background: black;">

			<section>
				<table>
					<div class="d-flex justify-content-around align-items-center">
						<div class="items-detail col-2" style="margin-bottom: 5px;">
							<img class="col-12"
								src="${pageContext.request.contextPath}/init/getProductImage?id=${productInfo.productId}">
						</div>
						<div class="items-detail col-2 text-center">${productName}</div>
						<div class="items-detail col-2 text-center tx-sm"
							style="font-size: small">${content1}${content2}</div>
						<div class="items-detail col-2 text-center" id="cost">${price}</div>
						<div class="amount items-detail col-2 text-center"
							style="font-size: small;">${qty}</div>
						<div class="total items-detail col-2 text-center">
							<c:out value="${qty*price}" />
						</div>
					</div>
				</table>
			</section>


			<hr style="background: black;">
			<div class="check">
				<div class="check-kid">總金額</div>
				<div class="total">
					<c:out value="${qty*price}" />
				</div>
			</div>
			<hr style="background: black;">
		</div>


		<form action="<c:url value='/order/orderCheck' />">
			<div class="row">
				<div class="formBox col-7">

					<label for="orderInfo" class="col-sm-6">訂單資訊</label>

					<div class="form-group row">
						<label for="name" class="col-sm-3 ml-3">姓名</label> <input
							type="text" class="form-control  col-6" name="name" 
							placeholder="Name">
					</div>
					<div class="form-group row">
						<label for="address" class="col-sm-3 ml-3">地址</label> <input
							type="text" class="form-control col-6" name="address"
							placeholder="Address">
					</div>
					<div class="form-group row">
						<label for="address" class="col-sm-3 ml-3">電話</label> <input
							type="text" class="form-control col-6" name="phone"
							placeholder="Phone">
					</div>
					<div class="form-group row">
						<label for="payment" class="col-sm-3 ml-3">付款方式</label> <select
							class="form-control form-control-sm col-6">
							<option>信用卡付款</option>
						</select>
					</div>

					<button type="submit" class="btn btn-primary ml-3">Submit</button>

				</div>
				<div class="textAreaBox col-5">
					<label for="">備註</label>
					<textarea class="form-control w-75 h-50"></textarea>
				</div>
			</div>
		</form>
	</div>






	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>